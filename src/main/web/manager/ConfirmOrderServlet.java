package main.web.manager;


import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmOrderServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BussinessServiceImpl service = new BussinessServiceImpl();
        String orderid = request.getParameter("orderid");
        service.confirmOrder(orderid);
        request.setAttribute("message", "订单已确认，成功发货");
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
