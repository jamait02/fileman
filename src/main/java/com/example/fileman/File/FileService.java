package com.example.fileman.File;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class FileService {

    public static int addFile(JdbcTemplate jdbcTemplate, String name, int directory_id, int created_by, int updated_by) {
        Date current_timestamp = new Date(System.currentTimeMillis());
        String query = "INSERT INTO \"file\" (name, directory_id, created_at, created_by, updated_at, updated_by) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, name, directory_id, current_timestamp, created_by, current_timestamp, updated_by);
    }

    public static int deleteFile(JdbcTemplate jdbcTemplate, int id) {
        String query = "DELETE FROM \"file\" WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    public static int updateFile(JdbcTemplate jdbcTemplate, int id, String name, int directory_id, int updated_by) {
        String query = "UPDATE \"file\" SET name = ?, directory_id = ?, updated_at = ? , updated_by = ? WHERE id = ?";
        return jdbcTemplate.update(query, name, directory_id, new Date(System.currentTimeMillis()), updated_by, id);
    }

    public static File getFileById(JdbcTemplate jdbcTemplate, int id) {
        String query = "SELECT * FROM \"file\" WHERE id = ?";
        return jdbcTemplate.query(query, new FileMapper(), id).get(0);
    }

    public static File getFileByName(JdbcTemplate jdbcTemplate, String name) {
        String query = "SELECT * FROM \"file\" WHERE name = ?";
        return jdbcTemplate.query(query, new FileMapper(), name).get(0);
    }
}
