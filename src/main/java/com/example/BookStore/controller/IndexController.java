package com.example.BookStore.controller;

import com.example.BookStore.dao.UserDao;
import com.example.BookStore.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/front/*")
public class IndexController {

    @Autowired
    private UserDao userDao;

    //index页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //登录页面
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    //注册方法
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if(password.equals(password2)){
            Users users = new Users();
            users.setUsername(username);
            users.setPw(password);
            userDao.save(users);
            return "index";
        }
        else {
            return "register";
        }
    }

    //登录方法
    @RequestMapping("/addlogin")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Users user = userDao.findByUsernameAndPassword(username,password);
        String str = "";
        if(user != null)
            str = "index";
        else str = "login";

        return str;
    }
}
