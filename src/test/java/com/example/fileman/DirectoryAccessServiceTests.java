package com.example.fileman;

import com.example.fileman.Directory.DirectoryAccess.DirectoryAccess;
import com.example.fileman.Directory.DirectoryAccess.DirectoryAccessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
public class DirectoryAccessServiceTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAddDirectoryAccess() {
        // Perform the addDirectoryAccess() operation
        int affected_rows = DirectoryAccessService.addDirectoryAccess(jdbcTemplate, 1, 2, "rwx");

        if (affected_rows == 1) {
            assert(true);
            // Delete the test directory access
            DirectoryAccessService.deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId(jdbcTemplate, 1,2);
        } else {
            assert(false);
        }
    }

    @Test
    public void testGetDirectoryAccessById() {
        // Get the directory access
        DirectoryAccess directory_access = DirectoryAccessService.getDirectoryAccessById(jdbcTemplate, 1);

        // Check if the directory access data matches
        assert(directory_access.getId() == 1);
        assert(directory_access.getDirectory_id() == 1);
        assert(directory_access.getGroup_id() == 1);
    }

    @Test
    public void testGetDirectoryAccessByDirectoryId() {
        // Get the directory access
        List<DirectoryAccess> directory_access = DirectoryAccessService.getDirectoryAccessByDirectoryId(jdbcTemplate, 1);

        // Check if the directory access data matches
        try {
            for (DirectoryAccess access : directory_access) {
                if (access.getDirectory_id() == 1) {
                    assert(true);
                    break;
                }
            }
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void testGetDirectoryAccessByGroupId() {
        // Get the directory access
        List<DirectoryAccess> directory_access = DirectoryAccessService.getDirectoryAccessByGroupId(jdbcTemplate, 1);

        // Check if the directory access data matches
        try {
            for (DirectoryAccess access : directory_access) {
                if (access.getGroup_id() == 1) {
                    assert(true);
                    break;
                }
            }
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void testUpdateDirectoryAccessByDirectoryIdAndGroupId() {
        // Perform the updateDirectoryAccess() operation
        int affected_rows = DirectoryAccessService.updateDirectoryAccessByDirectoryIdAndGroupId(jdbcTemplate, 1, 1, "r-w");

        if (affected_rows == 1) {
            assert(true);
            // Reset the test directory access
            DirectoryAccessService.updateDirectoryAccessByDirectoryIdAndGroupId(jdbcTemplate, 1, 1, "rwx");
        } else {
            assert(false);
        }
    }

    @Test
    public void deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId() {
        // Perform the deleteDirectoryAccess() operation
        int affected_rows = DirectoryAccessService.deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId(jdbcTemplate, 2, 2);

        if (affected_rows == 1) {
            // Delete the test directory access
            DirectoryAccessService.addDirectoryAccess(jdbcTemplate, 2,2, "rwx");
        }

        // Check if the operation was successful
        assert(affected_rows == 1);
    }
}
