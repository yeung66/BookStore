package main.test;

import main.dao.impl.CategoryDaoImpl;
import main.domain.Category;
import org.junit.Test;

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
    }

}