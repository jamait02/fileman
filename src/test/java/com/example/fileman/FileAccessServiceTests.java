package com.example.fileman;

import com.example.fileman.File.FileAccess.FileAccess;
import com.example.fileman.File.FileAccess.FileAccessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class FileAccessServiceTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAddFileAccess() {
        // Perform the addFileAccess() operation
        int affected_rows = FileAccessService.addFileAccess(jdbcTemplate, 1, 2, "rwx");

        if (affected_rows == 1) {
            // Delete the test file access
            FileAccessService.deleteFileAccessByFileIdAndGroupId(jdbcTemplate, 1, 2);
        }

        // Check if the operation was successful
        assert(affected_rows == 1);
    }

    @Test
    public void testGetFileAccessById() {
        // Get the file access
        FileAccess file_access = FileAccessService.getFileAccessById(jdbcTemplate, 1);

        // Check if the file access data matches
        assert(file_access.getId() == 1);
        assert(file_access.getFile_id() == 1);
        assert(file_access.getGroup_id() == 1);
    }

    @Test
    public void testUpdateFileAccessByFileIdAndGroupId() {
        // Perform the addFileAccess() operation
        int affected_rows = FileAccessService.updateFileAccessByFileIdAndGroupId(jdbcTemplate, 1, 1, "r-x");

        if (affected_rows == 1) {
            assert(true);
            // Reset the test file access
            FileAccessService.updateFileAccessByFileIdAndGroupId(jdbcTemplate, 1, 1, "rwx");
        } else {
            assert(false);
        }
    }

    @Test
    public void testDeleteFileAccessByFileIdAndGroupId() {
        // Perform the addFileAccess() operation
        int affected_rows = FileAccessService.deleteFileAccessByFileIdAndGroupId(jdbcTemplate, 1, 1);

        if (affected_rows == 1) {
            assert (true);
            // Delete the test file access
            affected_rows = FileAccessService.addFileAccess(jdbcTemplate, 1, 1, "rwx");
        } else {
            assert (false);
        }
    }
}
