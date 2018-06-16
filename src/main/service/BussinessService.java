package main.service;

import main.domain.Book;
import main.domain.Cart;
import main.domain.Category;
import main.domain.Page;

import java.util.List;

public interface BussinessService {
    //获取所有分类
    List<Category> getAllCategory();
    //查找某一分类
    Category findCategory(String categoryID);
    //添加书
    void addBook(Book book);
    //获得书
    Book findBook(String bookID);
    //获得分页数据，不分类
    Page getBookPageData(String pagenum);
    //获取分类下的分页数据,pagenum，类型
    Page getBookPageData(String pagenum, String categoryID);
    //购买书籍
    void buyBook(Cart cart, Book book);
}
