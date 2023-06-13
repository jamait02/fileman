package com.example.fileman.Directory.DirectoryAccess;

public class DirectoryAccess {
    private Integer id;
    private Integer directory_id;
    private Integer group_id;
    private String access_type;

    //region Getter and Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getDirectory_id() { return directory_id; }
    public void setDirectory_id(Integer directory_id) { this.directory_id = directory_id; }

    public Integer getGroup_id() { return group_id; }
    public void setGroup_id(Integer group_id) { this.group_id = group_id; }

    public String getAccess_type() { return access_type; }
    public void setAccess_type(String access_type) { this.access_type = access_type; }
    //endregion
}
