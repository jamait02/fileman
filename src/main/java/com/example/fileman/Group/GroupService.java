package com.example.fileman.Group;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    public static int addGroup(JdbcTemplate jdbcTemplate, String name, String role) {
        String query = "INSERT INTO \"group\" (name, role) VALUES (?, ?)";
        return jdbcTemplate.update(query, name, role);
    }

    public static int deleteGroup(JdbcTemplate jdbcTemplate, int id) {
        String query = "DELETE FROM \"group\" WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    public static int updateGroup(JdbcTemplate jdbcTemplate, int id, String name, String role) {
        String query = "UPDATE \"group\" SET name = ?, role = ? WHERE id = ?";
        return jdbcTemplate.update(query, name, role, id);
    }
    public static Group getGroupById(JdbcTemplate jdbcTemplate, int id) {
        String query = "SELECT * FROM \"group\" WHERE id = ?";
        return jdbcTemplate.query(query, new GroupMapper(), id).get(0);
    }

    public static Group getGroupByName(JdbcTemplate jdbcTemplate, String name) {
        String query = "SELECT * FROM \"group\" WHERE name = ?";
        return jdbcTemplate.query(query, new GroupMapper(), name).get(0);
    }
}
