package main.web.client;

import main.domain.Order;
import main.domain.User;
import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientListOrderServlet")
public class ClientListOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BussinessServiceImpl service = new BussinessServiceImpl();

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("message", "您还没登录");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        String userid = ((User) request.getSession().getAttribute("user")).getUserid();
        List<Order> orders = service.clientListOrder(userid);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }
}
