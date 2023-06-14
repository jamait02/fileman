package com.example.fileman.File.FileAccess;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FileAccessService {
    public static int addFileAccess(JdbcTemplate jdbcTemplate, int group_id, int file_id, String access_type) {
        String query = "INSERT INTO file_access (file_id, group_id, access_type) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, group_id, file_id, access_type);
    }

    public static int deleteFileAccessByFileIdAndGroupId(JdbcTemplate jdbcTemplate, int group_id, int file_id) {
        String query = "DELETE FROM file_access WHERE group_id = ? AND file_id = ?";
        return jdbcTemplate.update(query, group_id, file_id);
    }

    public static int updateFileAccessByFileIdAndGroupId(JdbcTemplate jdbcTemplate, int group_id, int file_id, String access_type) {
        String query = "UPDATE file_access SET access_type = ? WHERE group_id = ? AND file_id = ?";
        return jdbcTemplate.update(query, access_type, group_id, file_id);
    }

    public static List <FileAccess> getFileAccessByGroupId(JdbcTemplate jdbcTemplate, int group_id) {
        String query = "SELECT * FROM file_access WHERE group_id = ?";
        return jdbcTemplate.query(query, new FileAccessMapper(), group_id);
    }
}
