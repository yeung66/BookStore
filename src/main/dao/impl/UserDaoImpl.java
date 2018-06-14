package main.dao.impl;

import main.dao.UserDao;
import main.domain.User;
import main.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/** * 用户的登录注册模块
 * * 1：登陆
 * * 2：注册
 * * 3：根据id查找具体的用户 */

public class UserDaoImpl implements UserDao{
    /**
    * @Author: Jinglin Chen
    * @Description: 注册
    * @Date: 9:15 2018/6/14
    * @param:  User user
    */
    public void register(User user) {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "INSERT INTO users (userID,userName,pw) VALUES(?,?,?)";
        try {
            queryRunner.update(sql, new Object[]{user.getUserid(),user.getUsername(), user.getPw()});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * @Author: Jinglin Chen
    * @Description: 登录
    * @Date: 9:21 2018/6/14
    * @param:  String username, password
    */
    public User login(String username, String password) {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "SELECT * FROM users WHERE username = ? AND pw=?";
        try {
            return (User) queryRunner.query(sql, new Object[]{username, password}, new BeanHandler(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * @Author: Jinglin Chen
    * @Description: 查询
    * @Date: 9:22 2018/6/14
    * @param:  String id
    */
    public User find(String id) {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "SELECT * FROM users WHERE userID = ?";
        try { return (User) queryRunner.query(sql, id, new BeanHandler(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

