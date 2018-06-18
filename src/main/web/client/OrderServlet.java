package main.web.client;

import main.domain.Cart;
import main.domain.User;
import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BussinessServiceImpl service = new BussinessServiceImpl();
        try {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart == null) {
                request.setAttribute("message", "您的购物车还没有商品哦");
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                return;
            }
            User user = (User) request.getSession().getAttribute("user");
            service.createOrder(cart,user);
            request.setAttribute("message", "您的订单已经生成！");
            request.getSession().removeAttribute("cart");
            request.getRequestDispatcher("/message.jsp").forward(request, response);


        }catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "生成订单失败了");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
