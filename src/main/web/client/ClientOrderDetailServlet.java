package main.web.client;

import main.domain.Order;
import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientOrderDetailServlet")
public class ClientOrderDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderID = request.getParameter("orderID");
        BussinessServiceImpl service = new BussinessServiceImpl();
        Order order = service.findOrder(orderID);
        request.setAttribute("order", order);
        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
    }
}
