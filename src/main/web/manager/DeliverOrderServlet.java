package main.web.manager;

import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//管理端发货
@WebServlet(name = "DeliverOrderServlet")
public class DeliverOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BussinessServiceImpl service = new BussinessServiceImpl();
        String orderID = request.getParameter("orderID");
        service.deliveryOrder(orderID);
        request.setAttribute("message", "订单已确认，成功发货");
//        request.getRequestDispatcher("order.jsp").forward(request, response);
    }
}
