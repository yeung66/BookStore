package com.example.BookStore.domain;

/**
 * @author : CWQ
 * @Description : Category实体类
 * @date :2018-06-12
 **/
public class Category {
    private String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
