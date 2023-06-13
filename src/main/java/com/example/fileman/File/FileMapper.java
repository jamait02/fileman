package com.example.fileman.File;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileMapper implements RowMapper<File> {
    public File mapRow(ResultSet rs, int rowNum) throws SQLException {
        File file = new File();
        file.setId(rs.getInt("id"));
        file.setName(rs.getString("name"));
        file.setDirectory_id(rs.getInt("directory_id"));
        file.setCreated_at(rs.getDate("created_at"));
        file.setCreated_by(rs.getInt("created_by"));
        file.setUpdated_at(rs.getDate("updated_at"));
        file.setUpdated_by(rs.getInt("updated_by"));
        return file;
    }
}
