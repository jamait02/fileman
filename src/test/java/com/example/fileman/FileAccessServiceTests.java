package com.example.fileman;

import com.example.fileman.Directory.DirectoryAccess.DirectoryAccess;
import com.example.fileman.File.FileAccess.FileAccess;
import com.example.fileman.File.FileAccess.FileAccessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
        List<FileAccess> file_access = FileAccessService.getFileAccessByGroupId(jdbcTemplate, 1);

        try {
            for (FileAccess access : file_access) {
                if (access.getFile_id() == 1) {
                    assert(true);
                    break;
                }
            }
        } catch (Exception e) {
            assert(false);
        }
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
