package com.example.BookStore.test;

import com.example.BookStore.dao.impl.CategoryDaoImpl;
import com.example.BookStore.domain.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryDaoImplTest {
    @Test
    public void findCategory() throws Exception {
        String id = "1";
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        Category category = categoryDao.findCategory(id);
        System.out.println(category.getName());
    }

    @Test
    public void getAllCategory() throws Exception {
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        List<Category> categories = categoryDao.getAllCategory();
        for(Category category: categories){
            System.out.println(category.getName());
        }
    }

}