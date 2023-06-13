package com.example.fileman.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static int addUser(JdbcTemplate jdbcTemplate, String firstname, String lastname, String email, String password, int group_id) {
        String query = "INSERT INTO \"user\" (firstname, lastname, email, password, group_id) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, firstname, lastname, email, password, group_id);
    }

    public static int deleteUser(JdbcTemplate jdbcTemplate, int id) {
        String query = "DELETE FROM \"user\" WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    public static int updateUser(JdbcTemplate jdbcTemplate, int id, String firstname, String lastname, String email, String password, int group_id) {
        String query = "UPDATE \"user\" SET firstname = ?, lastname = ?, email = ?, password = ?, group_id = ? WHERE id = ?";
        return jdbcTemplate.update(query, firstname, lastname, email, password, group_id, id);
    }

    public static User getUserByEmailAndPassword(JdbcTemplate jdbcTemplate, String email, String password) {
        String query = "SELECT * FROM \"user\" WHERE email = ? AND password = ?";
        return jdbcTemplate.query(query, new UserMapper(), email, password).get(0);
    }
}
