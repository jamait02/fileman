package com.example.fileman.File;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class File {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer directory_id;
    private Date created_at;
    private Integer created_by;
    private Date updated_at;
    private Integer updated_by;

    //region Getter and Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getDirectory_id() { return directory_id; }
    public void setDirectory_id(Integer directory_id) { this.directory_id = directory_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getCreated_at() { return created_at; }
    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public Integer getCreated_by() { return created_by; }
    public void setCreated_by(Integer created_by) { this.created_by = created_by; }

    public Date getUpdated_at() { return updated_at; }
    public void setUpdated_at(Date updated_at) { this.updated_at = updated_at; }

    public Integer getUpdated_by() { return updated_by; }
    public void setUpdated_by(Integer updated_by) { this.updated_by = updated_by; }
    //endregion
}
