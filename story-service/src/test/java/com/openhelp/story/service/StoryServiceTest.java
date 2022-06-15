package com.openhelp.story.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhelp.story.dto.ListDto;
import com.openhelp.story.dto.story.StoryDto;
import com.openhelp.story.dto.story.StoryFilterDto;
import com.openhelp.story.enums.EntityType;
import com.openhelp.story.enums.OperationType;
import com.openhelp.story.mapper.StoryMapper;
import com.openhelp.story.model.Story;
import com.openhelp.story.repository.StoryRepository;
import com.openhelp.story.repository.filter.StoryFilter;
import com.openhelp.story.utils.SecurityUtils.AccessDto;
import com.openhelp.story.utils.SecurityUtils.UserAccessDto;
import com.openhelp.story.validation.ConcurrentUpdatedException;
import com.openhelp.story.validation.NoSuchStoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static com.openhelp.story.repository.StoryRepository.StorySpecification;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class StoryServiceTest {

    @Autowired
    StoryService storyService;

    @MockBean
    StoryMapper storyMapper;

    @MockBean
    StoryRepository storyRepository;

    Long id = 1L;

    String title = "title";

    @BeforeEach
    public void before() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        AccessDto create = AccessDto.builder()
                .entityType(EntityType.STORY)
                .operationType(OperationType.CREATE)
                .build();
        AccessDto readAny = AccessDto.builder()
                .entityType(EntityType.STORY)
                .operationType(OperationType.READ_ANY)
                .build();
        AccessDto updateAny = AccessDto.builder()
                .entityType(EntityType.STORY)
                .operationType(OperationType.UPDATE_ANY)
                .build();
        AccessDto deleteAny = AccessDto.builder()
                .entityType(EntityType.STORY)
                .operationType(OperationType.DELETE_ANY)
                .build();
        UserAccessDto userAccess = new UserAccessDto(
                1L, List.of(create, readAny, updateAny, deleteAny));
        try {
            String json = new ObjectMapper().writeValueAsString(userAccess);
            request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, json);
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Get story list")
    public void whenGetListThenReturnListDto() {
        Story story = Story.builder().id(id).build();
        StoryDto storyDto = StoryDto.builder().id(id).build();
        StoryFilterDto filterDto = new StoryFilterDto();
        filterDto.setPageNumber(0);
        filterDto.setPageSize(10);
        filterDto.setTitle(title);
        StoryFilter storyFilter = new StoryFilter();
        storyFilter.setTitle(title);
        given(storyMapper.storyFilterDtoToStoryFilter(filterDto)).willReturn(storyFilter);
        Page<Story> page = new PageImpl<>(List.of(story), PageRequest.of(0, 1), 1);
        given(storyRepository.findAll(
                any(StorySpecification.class), any(Pageable.class))
        ).willReturn(page);
        given(storyMapper.storyToStoryDto(story))
                .willReturn(storyDto);

        ListDto<StoryDto> actual = storyService.getList(filterDto);

        Assertions.assertEquals(actual.getTotal(), 1);
        Assertions.assertEquals(actual.getItems(), List.of(storyDto));
        verify(storyRepository, times(1))
                .findAll(any(StorySpecification.class), any(Pageable.class));
        verify(storyMapper, times(1))
                .storyFilterDtoToStoryFilter(filterDto);
        verify(storyMapper, times(1)).storyToStoryDto(story);
    }

    @Test
    @DisplayName("Find story by id")
    public void whenFindByIdThenReturnStoryDto() {
        Story story = Story.builder().id(id).build();
        StoryDto expected = StoryDto.builder().id(id).build();
        given(storyRepository.findById(id))
                .willReturn(Optional.of(story));
        given(storyMapper.storyToStoryDto(story))
                .willReturn(expected);

        StoryDto actual = storyService.findById(id);

        Assertions.assertEquals(actual, expected);
        verify(storyRepository, times(1)).findById(id);
        verify(storyMapper, times(1)).storyToStoryDto(story);
    }

    @Test
    @DisplayName("No such story by id")
    public void whenFindByIdThenThrowNoSuchStoryException() {
        given(storyRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(NoSuchStoryException.class,
                () -> storyService.findById(id), "");

        verify(storyMapper, never()).storyToStoryDto(any());
        verify(storyRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Create new story")
    public void whenCreateNewStorySuccessfullyThenReturnNewStoryId() {
        Story story = Story.builder().title(title).build();
        Story saved = Story.builder().id(id).title(title).build();
        StoryDto storyDto = StoryDto.builder().title(title).build();
        given(storyMapper.storyDtoToStory(storyDto)).willReturn(story);
        given(storyRepository.save(story)).willReturn(saved);

        Long actualId = storyService.create(storyDto);

        Assertions.assertEquals(actualId, id);
        verify(storyRepository, times(1)).save(story);
        verify(storyMapper, times(1)).storyDtoToStory(storyDto);
    }

    @Test
    @DisplayName("Update story success")
    public void whenUpdateStorySuccessfullyThenReturnUpdatedStoryId() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        StoryDto storyDto = StoryDto.builder().title(title).updatedAt(now.getTime()).build();
        Story story = Story.builder().title(title).updatedAt(now).build();
        given(storyRepository.findById(id)).willReturn(
                Optional.of(Story.builder().updatedAt(now).build()));
        given(storyMapper.storyDtoToStory(storyDto)).willReturn(story);

        Long actualId = storyService.update(id, storyDto);

        Assertions.assertEquals(actualId, id);
        verify(storyRepository, times(1)).save(story);
        verify(storyMapper, times(1)).storyDtoToStory(storyDto);
    }

    @Test
    @DisplayName("Update story not exist")
    public void whenUpdateNotExistedStoryThenThrowNoSuchStoryException() {
        StoryDto storyDto = StoryDto.builder().title(title).build();

        assertThrows(NoSuchStoryException.class,
                () -> storyService.update(id, storyDto), "");

        verify(storyMapper, never()).storyDtoToStory(any());
    }

    @Test
    @DisplayName("Update story concurrent update exception")
    public void whenUpdateNotExistedStoryThenThrowConcurrentUpdateException() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        StoryDto storyDto = StoryDto.builder().title(title).updatedAt(now.getTime()).build();
        Story story = Story.builder().title(title).updatedAt(now).build();
        given(storyRepository.findById(id)).willReturn(
                Optional.of(Story.builder()
                        .updatedAt(new Timestamp(System.currentTimeMillis()))
                        .build()));
        given(storyMapper.storyDtoToStory(storyDto)).willReturn(story);

        assertThrows(ConcurrentUpdatedException.class,
                () -> storyService.update(id, storyDto), "");

        verify(storyMapper, never()).storyDtoToStory(any());
    }

    @Test
    @DisplayName("Delete story success")
    public void whenDeleteStorySuccessfullyThenReturnStoryId() {
        given(storyRepository.findById(id)).willReturn(Optional.of(Story.builder().build()));

        Long actualId = storyService.delete(id);

        Assertions.assertEquals(actualId, id);
        verify(storyRepository, times(1))
                .updateDeletedAtById(eq(id), any(Timestamp.class));
    }

    @Test
    @DisplayName("Delete story not exist")
    public void whenDeleteNotExistedStoryThenThrowNoSuchStoryException() {
        given(storyRepository.findById(id)).willReturn(Optional.empty());

        assertThrows(NoSuchStoryException.class,
                () -> storyService.delete(id), "");

        verify(storyRepository, never())
                .updateDeletedAtById(eq(id), any(Timestamp.class));
    }
}