package main.dao.impl;

import main.dao.BookDao;
import main.domain.Book;
import main.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public void add(Book book) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into book(bookID,bookName,price,type,bookPicture) values(?,?,?,?,?,?)";
            Object params[] = {book.getBookid(),book.getBookname(),book.getPrice(),book.getType(),book.getBookpicture()};
            runner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book find(String id) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from book where bookID=?";
            return runner.query(sql, new BeanHandler<>(Book.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getPageData(int startindex, int pagesize) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from book limit ?,?";
            Object params[] = {startindex, pagesize};
            return runner.query(sql, new BeanListHandler<>(Book.class),params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalRecord() {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select count(*) from book";
            long totalRecord = (Long)runner.query(sql, new ScalarHandler());
            return (int)totalRecord;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getCategoryPageData(int startindex, int pagesize, String type) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from book where type=? limit ?,?";
            Object params[] = {type, startindex, pagesize};
            return runner.query(sql, new BeanListHandler<>(Book.class) ,params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCategoryTotalRecord(String type) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select count(*) from book where type=?";
            long totalRecord = (Long)runner.query(sql, new ScalarHandler(),type);
            return (int)totalRecord;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
