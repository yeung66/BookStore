package main;

import main.dao.impl.CategoryDaoImpl;
import main.domain.Book;
import main.domain.Category;

public class Main {
    public static void  main(String args[]){
        String id = "1";
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        Category category = categoryDao.findCategory(id);
        System.out.println(category.getName());
    }
}
