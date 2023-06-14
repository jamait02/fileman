package com.example.fileman.Directory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Directory {
    @Id
    @GeneratedValue
    private Integer id;
    private String path;
    private Date created_at;
    private Integer created_by;
    private Date updated_at;
    private Integer updated_by;

    //region Getter and Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

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
