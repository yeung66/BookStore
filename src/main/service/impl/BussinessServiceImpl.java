package main.service.impl;

import main.dao.BookDao;
import main.dao.CategoryDao;
import main.dao.OrderDao;
import main.dao.UserDao;
import main.domain.*;
import main.service.BussinessService;
import main.utils.DaoFactory;
import main.utils.WebUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class BussinessServiceImpl implements BussinessService {
    private CategoryDao categoryDao = DaoFactory.getInstance().createDao("main.dao.impl.CategoryDaoImpl", CategoryDao.class);
    private BookDao bookDao = DaoFactory.getInstance().createDao("main.dao.impl.BookDaoImpl", BookDao.class);
    private UserDao userDao = DaoFactory.getInstance().createDao("main.dao.impl.UserDaoImpl", UserDao.class);
    private OrderDao orderDao = DaoFactory.getInstance().createDao("main.dao.impl.OrderDaoImpl", OrderDao.class);

    //获取所有分类
    public List<Category> getAllCategory(){
        return categoryDao.getAllCategory();
    }
    //查找某一分类
    public Category findCategory(String categoryID){
        return categoryDao.findCategory(categoryID);
    }
    //添加书
    public void addBook(Book book){
        bookDao.add(book);
    }
    //获得书
    public Book findBook(String bookID){
        return bookDao.find(bookID);
    }
    //获得分页数据，不分类
    public Page getBookPageData(String pagenum){
        int totalrecord = bookDao.getTotalRecord();
        Page page = null;
        if(pagenum == null){
            page = new Page(1,totalrecord);
        }else{
            page = new Page(Integer.parseInt(pagenum), totalrecord);
        }
        List<Book> list = bookDao.getPageData(page.getStartindex(), page.getPagesize());
        page.setList(list);
        return page;
    }
    //获取分类下的分页数据,pagenum，类型
    public Page getBookPageData(String pagenum, String categoryID){
        int totalrecord = bookDao.getCategoryTotalRecord(categoryID);
        Page page = null;
        if(pagenum == null){
            page = new Page(1,totalrecord);
        }else{
            page = new Page(Integer.parseInt(pagenum), totalrecord);
        }
        List<Book> list = bookDao.getCategoryPageData(page.getStartindex(), page.getPagesize(), categoryID);
        page.setList(list);
        return page;
    }
    //购买书籍
    public void buyBook(Cart cart, Book book) {
        cart.addBook2Cart(book);
    }


    //删除购物车内
    public void deleteCartBook(Cart cart, Book book){
        cart.deleteBookFromCart(book);
    }

//=======
//    //删除书籍
//    public void deleteBook(Cart cart, Book book){
//        cart.deleteBookFromCart(book);
//    }
//>>>>>>> temp
    //下订单
    public void createOrder(Cart cart, User user){
        if(cart == null){
            throw new RuntimeException("购物车里为空");
        }
        Order order = new Order();
        order.setOrderID(WebUtils.makeID());
        order.setOrderTime(new Date());
        order.setPrice(cart.getPrice());
        order.setState("待发货");
        order.setUser(user);
        for(Map.Entry<String, CartItem> me : cart.getMap().entrySet()){
            //
            CartItem citem = me.getValue();
            OrderItem oitem = new OrderItem();
            oitem.setBook(citem.getBook());
            oitem.setPrice(citem.getPrice());
            oitem.setId(WebUtils.makeID());
            oitem.setQuantity(citem.getQuantity());
            order.getOrderitems().add(oitem);
        }
        orderDao.add(order);
    }
    //列出已发货或未发货的所有订单
    public List<Order> listOrder(String state) {
        return orderDao.getAll(state);
    }
    //查找订单
    public Order findOrder(String orderid) {
        return orderDao.find(orderid);
    }

    //发货(管理端）
    public void deliveryOrder(String orderID) {
        Order order = orderDao.find(orderID);
        order.setState("待收货");
        orderDao.update(order);
    }
    //确认收货(客户端）
    public void comfirmOrder(String orderID){
        Order order = orderDao.find(orderID);
        order.setState("已收货");
        orderDao.update(order);
    }
    //列出某个用户已发货或未发货的所有订单（管理端用）
    public List<Order> listOrder(String state, String userid) {
        return orderDao.getAll(state, userid);
    }
    //列出某用户的所有订单（客户端用）
    public List<Order> clientListOrder(String userid) {
        return orderDao.getAllOrder(userid);
    }
    //删除某本书(管理端用）
    public void deleteBook(String bookID){
        bookDao.delete(bookID);
    }
}
