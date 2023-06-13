package com.example.fileman;

import com.example.fileman.Group.Group;
import com.example.fileman.Group.GroupService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class GroupServiceTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAddGroup() {
        // Test data
        String name = "Test_Group_Name";
        String role = "Test_Role";

        // Perform the addGroup() operation
        int affected_rows = GroupService.addGroup(jdbcTemplate, name, role);

        // Check if the operation was successful
        Assertions.assertEquals(1, affected_rows);
    }

    @Test
    public void testGetGroupByName() {
        // Test data
        String name = "Testgroup";

        // Call the method
        Group group = GroupService.getGroupByName(jdbcTemplate, name);

        // Assert the result
        Assertions.assertNotNull(group);
        Assertions.assertEquals(name, group.getName());
    }

    @Test
    public void testGetGroupById() {
        // Test data
        int id = 1;

        // Call the method
        Group group = GroupService.getGroupById(jdbcTemplate, id);

        // Assert the result
        Assertions.assertNotNull(group);
        Assertions.assertEquals(id, group.getId());
    }

    @Test
    public void testUpdateGroup() {
        // Test data
        int id = GroupService.getGroupByName(jdbcTemplate, "Test_Group_Name").getId();
        String newName = "UpdatedGroup";
        String newRole = "UpdatedRole";

        // Call the method
        int rowsAffected = GroupService.updateGroup(jdbcTemplate, id, newName, newRole);

        // Assert the result
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    public void testDeleteGroup() {
        // Test data
        int id = GroupService.getGroupByName(jdbcTemplate, "UpdatedGroup").getId();

        // Call the method
        int rowsAffected = GroupService.deleteGroup(jdbcTemplate, id);

        // Assert the result
        Assertions.assertEquals(1, rowsAffected);
    }
}
