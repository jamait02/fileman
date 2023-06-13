package com.example.fileman;

import com.example.fileman.File.File;
import com.example.fileman.File.FileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class FileServiceTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAddFile() {
        // Test data
        String name = "File1";
        int directoryId = 1; // Assuming a directory with ID 1 exists
        int createdBy = 1; // Assuming a user with ID 1 exists
        int updatedBy = 1; // Assuming a user with ID 1 exists

        // Call the method
        int rowsAffected = FileService.addFile(
                jdbcTemplate,
                name,
                directoryId,
                createdBy,
                updatedBy
        );

        // Assert the result
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    public void testGetFileByName() {
        // Test data
        String name = "testfile.txt";

        // Call the method
        File file = FileService.getFileByName(
                jdbcTemplate,
                name
        );

        // Assert the result
        Assertions.assertNotNull(file);
        Assertions.assertEquals(name, file.getName());
    }

    @Test
    public void testGetFileById() {
        // Test data
        int id = 1;

        // Call the method
        File file = FileService.getFileById(
                jdbcTemplate,
                id
        );

        // Assert the result
        Assertions.assertNotNull(file);
        Assertions.assertEquals(id, file.getId());
    }

    @Test
    public void testUpdateFile() {
        // Test data
        File file = FileService.getFileByName(
                jdbcTemplate,
                "File1"
        );
        int id = file.getId();
        String newName = "UpdatedFile";

        // Call the method
        int rowsAffected = FileService.updateFile(
                jdbcTemplate,
                id,
                newName,
                file.getDirectory_id(),
                file.getUpdated_by()
        );

        // Assert the result
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    public void testDeleteFile() {
        // Test data
        File file = FileService.getFileByName(
                jdbcTemplate,
                "UpdatedFile"
        );
        int id = file.getId();

        // Call the method
        int rowsAffected = FileService.deleteFile(jdbcTemplate, id);

        // Assert the result
        Assertions.assertEquals(1, rowsAffected);
    }
}
