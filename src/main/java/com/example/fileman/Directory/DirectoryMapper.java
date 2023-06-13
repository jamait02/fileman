package com.example.fileman.Directory;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectoryMapper implements RowMapper<Directory> {
    public Directory mapRow(ResultSet rs, int rowNum) throws SQLException {
        Directory directory = new Directory();
        directory.setId(rs.getInt("id"));
        directory.setPath(rs.getString("path"));
        directory.setCreated_at(rs.getDate("created_at"));
        directory.setCreated_by(rs.getInt("created_by"));
        directory.setUpdated_at(rs.getDate("updated_at"));
        directory.setUpdated_by(rs.getInt("updated_by"));
        return directory;
    }
}
