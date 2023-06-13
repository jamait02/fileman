package com.example.fileman.File.FileAccess;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileAccessMapper implements RowMapper<FileAccess> {
    public FileAccess mapRow(ResultSet rs, int rowNum) throws SQLException {
        FileAccess access = new FileAccess();
        access.setId(rs.getInt("id"));
        access.setFile_id(rs.getInt("file_id"));
        access.setGroup_id(rs.getInt("group_id"));
        access.setAccess_type(rs.getString("access_type"));
        return access;
    }
}
