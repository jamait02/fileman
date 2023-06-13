package com.example.fileman;

import com.example.fileman.User.User;
import com.example.fileman.User.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void getUserByEmailAndPassword() {
        // Get the user
        User user = UserService.getUserByEmailAndPassword(jdbcTemplate, "test@example.com", "password");

        // Check if the user data matches
        assert(user.getId() != null);
        assert(user.getFirstname().equals("Test"));
        assert(user.getLastname().equals("User"));
        assert(user.getEmail().equals("test@example.com"));
        assert(user.getPassword().equals("password"));
        assert(user.getGroupId() == 1);
    }

    @Test
    public void addUser() {
        // Create data for a new user
        String firstname = "John";
        String lastname = "Doe";
        String email = "johndoe@example.com";
        String password = "password";
        int group_id = 1;

        // Perform the addUser() operation
        int affected_rows = UserService.addUser(jdbcTemplate, firstname, lastname, email, password, group_id);

        // Check if the operation was successful
        assert(affected_rows == 1);
    }
    User testuser = UserService.getUserByEmailAndPassword(jdbcTemplate, "johndoe@example.com", "password");

    @Test
    public void updateUser() {
        // Update the testuser
        String firstname = "Sally";
        String lastname = "Smith";
        String email = "sallysmith@example.com";
        String password = "secret";
        int group_id = 1;

        // Perform the updateUser() operation
        int affected_rows = UserService.updateUser(jdbcTemplate, testuser.getId(), firstname, lastname, email, password, group_id);
        assert (affected_rows == 1);
    }

    @Test
    public void deleteUser() {
        // Delete the testuser
        int affected_rows = UserService.deleteUser(jdbcTemplate, testuser.getId());
        assert (affected_rows == 1);
    }
}
