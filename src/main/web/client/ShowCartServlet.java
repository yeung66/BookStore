package main.web.client;

import main.domain.Cart;
import main.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
* @Author: Jinglin Chen
* @Description: ShowCart
* @Date: 10:10 2018/6/14
*/
public class ShowCartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //先判断该用户是否登陆了
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("message", "您还没有登陆呢！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        } //如果登陆了.....
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //把该用户的购物车给JSP页面显示
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/client/listCart.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doGet(request, response);
    }
}
