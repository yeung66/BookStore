package com.example.BookStore.dao;

import com.example.BookStore.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<Users,Long>{

    public Users findByUsernameAndPassword(String username, String password);

}
