package main.service;

import main.domain.*;

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
    //删除书籍
    void deleteBook(Cart cart, Book book);
    //下订单
    void createOrder(Cart cart, User user);
    //列出已发货或未发货的所有订单
    List<Order> listOrder(String state);
    //列出某个用户的所有订单
    Order findOrder(String orderid);
    //更改订单状态
    void confirmOrder(String orderid);
    //列出某个用户已发货或未发货的所有订单
    List<Order> listOrder(String state, String userid);

    public List<Order> clientListOrder(String userid);
}
