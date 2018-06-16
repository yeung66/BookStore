package main.service.impl;

import main.dao.BookDao;
import main.dao.CategoryDao;
import main.dao.OrderDao;
import main.dao.UserDao;
import main.domain.Book;
import main.domain.Cart;
import main.domain.Category;
import main.domain.Page;
import main.service.BussinessService;
import main.utils.DaoFactory;

import java.util.List;

public class BussinessServiceImpl implements BussinessService {
    private CategoryDao categoryDao = DaoFactory.getInstance().createDao("dao.impl.CategoryDaoImpl", CategoryDao.class);
    private BookDao bookDao = DaoFactory.getInstance().createDao("dao.impl.BookDaoImpl", BookDao.class);
    private UserDao userDao = DaoFactory.getInstance().createDao("dao.impl.UserDaoImpl", UserDao.class);
    private OrderDao orderDao = DaoFactory.getInstance().createDao("dao.impl.OrderDaoImpl", OrderDao.class);

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

}
