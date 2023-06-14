package com.example.fileman.Directory;

import com.example.fileman.Directory.DirectoryAccess.DirectoryAccess;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DirectoryService {
    public static int addDirectory(JdbcTemplate jdbcTemplate, String path, int created_by, int updated_by) {
        String query = "INSERT INTO directory (path, created_by, updated_by) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, path, created_by, updated_by);
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

    public static List<Directory> getDirectoriesByDirectoryIds(JdbcTemplate jdbcTemplate, List<Integer> directory_ids) {
        String query = "SELECT * FROM directory WHERE id IN (";
        for (int i = 0; i < directory_ids.size(); i++) {
            query += "?";
            if (i != directory_ids.size() - 1) {
                query += ", ";
            }
        }
        query += ")";
        return jdbcTemplate.query(query, new DirectoryMapper(), directory_ids.toArray());
    }

    public static Directory getDirectoryByPath(JdbcTemplate jdbcTemplate, String path) {
        String query = "SELECT * FROM directory WHERE path = ?";
        return jdbcTemplate.query(query, new DirectoryMapper(), path).get(0);
    }
}
