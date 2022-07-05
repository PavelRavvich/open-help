package com.openhelp.group.mapper;

import com.openhelp.group.dto.membership.MembershipDto;
import com.openhelp.group.dto.membership.MembershipFilterDto;
import com.openhelp.group.model.Membership;
import com.openhelp.group.repository.filter.MembershipFilter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Pavel Ravvich.
 */
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DateMapper.class})
public interface MembershipMapper {

    MembershipFilter membershipFilterDtoToMembershipFilter(MembershipFilterDto dto);

    MembershipDto membershipToMembershipDto(Membership membership);

    @Mapping(target = "group", ignore = true)
    Membership membershipDtoToMembership(MembershipDto dto);
}
