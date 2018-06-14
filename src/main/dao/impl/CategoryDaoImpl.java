package main.dao.impl;



import main.dao.CategoryDao;
import main.domain.Category;
import main.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 *@Description : 目录DAO的实现类
 *@author : CWQ
 *@date :2018-6-12
 **/
public class CategoryDaoImpl implements CategoryDao {
    //查找分类项
    public Category findCategory(String id){
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from category where id=?";
            return (Category)runner.query(sql, new BeanHandler<>(Category.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //获取所有分类
    public List<Category> getAllCategory(){
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from category";
            return (List<Category>)runner.query(sql, new BeanListHandler(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
