package com.example.fileman;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class ControllerTests {
    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testController() {
        Controller app = new Controller(jdbcTemplate);
        assert(app.sayHello().equals("Hello world."));
    }
}
