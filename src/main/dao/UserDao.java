package main.dao;

import main.domain.User;

public interface UserDao {
    void register(User user);
    User login(String username, String password);
    User find(String id);

}
