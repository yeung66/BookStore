package main.web.client;

import main.domain.Book;
import main.domain.Cart;
import main.domain.User;
import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BussinessServiceImpl service = new BussinessServiceImpl();
        //先检查该用户是否登陆了
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("message", "您还没登陆，请登陆了再来查看");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return ; }
        //如果登陆了...
        // 得到该用户的购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //得到用户想删除的的书籍
        String book_id = request.getParameter("book_id");
        Book book = service.findBook(book_id);
        //删除书籍
        service.deleteBook(cart, book);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
