package com.example.fileman.Directory.DirectoryAccess;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DIrectoryAccessMapper implements RowMapper<DirectoryAccess> {
    public DirectoryAccess mapRow(ResultSet rs, int rowNum) throws SQLException {
        DirectoryAccess access = new DirectoryAccess();
        access.setId(rs.getInt("id"));
        access.setDirectory_id(rs.getInt("directory_id"));
        access.setGroup_id(rs.getInt("group_id"));
        access.setAccess_type(rs.getString("access_type"));
        return access;
    }
}
