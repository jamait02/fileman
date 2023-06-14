package com.example.fileman.File.FileAccess;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class FileAccess {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer file_id;
    private Integer group_id;
    private String access_type;

    //region Getter and Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getFile_id() { return file_id; }
    public void setFile_id(Integer file_id) { this.file_id = file_id; }

    public Integer getGroup_id() { return group_id; }
    public void setGroup_id(Integer group_id) { this.group_id = group_id; }

    public String getAccess_type() { return access_type; }
    public void setAccess_type(String access_type) { this.access_type = access_type; }
    //endregion
}
