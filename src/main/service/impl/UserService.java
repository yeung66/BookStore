package main.service.impl;

import main.dao.UserDao;
import main.domain.User;
import main.utils.DaoFactory;
/**
* @Author: Jinglin Chen
* @Description: UserService
* @Date: 10:13 2018/6/14
*/
public class UserService {
    private UserDao userDao = DaoFactory.getInstance().createDao("main.dao.impl.UserDaoImpl", UserDao.class);
    public void registerUser(User user) { userDao.register(user); }
    public User loginUser(String username,String password) { return userDao.login(username, password); }
    public User findUser(String id) { return userDao.find(id); }
}
