package com.openhelp.group;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
class GroupApplicationTests {

    @Test
    @DisplayName("Load Spring context")
    public void contextLoad() {
        GroupApplication.main(new String[] {});
    }

}