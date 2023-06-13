package com.example.fileman.Directory.DirectoryAccess;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryAccessService {
    public static int addDirectoryAccess(JdbcTemplate jdbcTemplate, int group_id, int directory_id, String access_type) {
        String query = "INSERT INTO directory_access (directory_id, group_id, access_type) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, group_id, directory_id, access_type);
    }

    public static int deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId(JdbcTemplate jdbcTemplate, int group_id, int directory_id) {
        String query = "DELETE FROM directory_access WHERE group_id = ? AND directory_id = ?";
        return jdbcTemplate.update(query, group_id, directory_id);
    }

    public static int updateDirectoryAccessByDirectoryIdAndGroupId(JdbcTemplate jdbcTemplate, int group_id, int directory_id, String access_type) {
        String query = "UPDATE directory_access SET access_type = ? WHERE group_id = ? AND directory_id = ?";
        return jdbcTemplate.update(query, access_type, group_id, directory_id);
    }

    public static DirectoryAccess getDirectoryAccessById(JdbcTemplate jdbcTemplate, int id) {
        String query = "SELECT * FROM directory_access WHERE id = ?";
        return jdbcTemplate.query(query, new DIrectoryAccessMapper(), id).get(0);
    }

    public static List <DirectoryAccess> getDirectoryAccessByDirectoryId(JdbcTemplate jdbcTemplate, int directory_id) {
        String query = "SELECT * FROM directory_access WHERE directory_id = ?";
        return jdbcTemplate.query(query, new DIrectoryAccessMapper(), directory_id);
    }

    public static List <DirectoryAccess> getDirectoryAccessByGroupId(JdbcTemplate jdbcTemplate, int group_id) {
        String query = "SELECT * FROM directory_access WHERE group_id = ?";
        return jdbcTemplate.query(query, new DIrectoryAccessMapper(), group_id);
    }

    public static DirectoryAccess getDirectoryAccessByDirectoryIdAndGroupId(JdbcTemplate jdbcTemplate, int directory_id, int group_id) {
        String query = "SELECT * FROM directory_access WHERE directory_id = ? AND group_id = ?";
        return jdbcTemplate.query(query, new DIrectoryAccessMapper(), directory_id, group_id).get(0);
    }
}
