package com.example.fileman;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileManApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void sayHello() {
        Controller app = new Controller(DatabaseConfig.jdbcTemplate());
        assert(app.sayHello().equals("Hello world."));
    }
}
