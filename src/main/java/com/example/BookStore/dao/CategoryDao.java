package com.example.BookStore.dao;

import com.example.BookStore.domain.Category;

import java.util.List;

/**
 *@Description : 目录DAO
 *@author : CWQ
 *@date :2018-6-12
 **/
public interface CategoryDao {
    //查找分类项
    Category findCategory(String id);
    //获取所有分类
    List<Category> getAllCategory();
}
