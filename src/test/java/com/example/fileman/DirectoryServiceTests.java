package com.example.fileman;

import com.example.fileman.Directory.Directory;
import com.example.fileman.Directory.DirectoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DirectoryServiceTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAddDirectory() {
        // Test data
        String path = "/path/to/directory";

        // Call the method
        int rowsAffected = DirectoryService.addDirectory(jdbcTemplate, path, 1, 1);

        // Assert the result
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    public void testGetDirectoryByPath() {
        // Test data
        String path = "/path/to/testdirectory";

        // Call the method
        try {
            Directory directory = DirectoryService.getDirectoryByPath(jdbcTemplate, path);
            assert (directory != null);
        } catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void testGetDirectoryById() {
        // Test data
        int id = 1;

        // Call the method
        try {
            Directory directory2 = DirectoryService.getDirectoryById(jdbcTemplate, id);
            assert (directory2 != null);
        } catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void testUpdateDirectory() {
        // Test data
        String path = "/path/to/directory";
        Directory directory = DirectoryService.getDirectoryByPath(jdbcTemplate, path);
        int id = directory.getId();
        String newPath = "/new/path/to/directory";

        // Call the method
        int rowsAffected = DirectoryService.updateDirectory(jdbcTemplate, id, newPath);

        // Assert the result
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    public void testDeleteDirectory() {
        // Test data
        String path = "/new/path/to/directory";
        Directory directory = DirectoryService.getDirectoryByPath(jdbcTemplate, path);
        int id = directory.getId();

        // Call the method
        int rowsAffected = DirectoryService.deleteDirectory(jdbcTemplate, id);

        // Assert the result
        Assertions.assertEquals(1, rowsAffected);
    }
}
