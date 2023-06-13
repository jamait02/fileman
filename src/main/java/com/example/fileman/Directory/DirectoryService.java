package com.example.fileman.Directory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class DirectoryService {
    public static int addDirectory(JdbcTemplate jdbcTemplate, String path, int created_by, int updated_by) {
        String query = "INSERT INTO directory (path, created_by, updated_by) VALUES (?, ?, ?)";
        int affectedRows = jdbcTemplate.update(query, path, created_by, updated_by);
        System.out.println(affectedRows);
        return affectedRows;
    }

    public static int deleteDirectory(JdbcTemplate jdbcTemplate, int id) {
        String query = "DELETE FROM directory WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    public static int updateDirectory(JdbcTemplate jdbcTemplate, int id, String path) {
        String query = "UPDATE directory SET path = ?, updated_at = ? WHERE id = ?";
        return jdbcTemplate.update(query, path, new Date(System.currentTimeMillis()), id);
    }

    public static Directory getDirectoryById(JdbcTemplate jdbcTemplate, int id) {
        String query = "SELECT * FROM directory WHERE id = ?";
        return jdbcTemplate.query(query, new DirectoryMapper(), id).get(0);
    }

    public static Directory getDirectoryByPath(JdbcTemplate jdbcTemplate, String path) {
        String query = "SELECT * FROM directory WHERE path = ?";
        return jdbcTemplate.query(query, new DirectoryMapper(), path).get(0);
    }
}
