package com.example.fileman;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DatabaseConfigTests {
    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplate() {
        try {
            String query = "SELECT 1";
            jdbcTemplate.queryForObject(query, Integer.class);
            assert(true);
        } catch (Exception e) {
            assert(false);
        }
    }
}
