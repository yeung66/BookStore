package main.web.client;

import main.domain.Book;
import main.service.BussinessService;
import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;

@WebServlet(name = "BookDetailServlet")
public class BookDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        BussinessService bussinessService = new BussinessServiceImpl();
        Book book = bussinessService.findBook(bookID);
        //获取各项值
        String bookName = book.getBookname();
        Double price = book.getPrice();
        String bookPicture = book.getBookpicture();
        String categoryID = book.getCategoryID();
        String categoryName = bussinessService.findCategory(categoryID).getName();
        //添加至属性
        request.setAttribute("bookName",bookName);
        request.setAttribute("price",price);
        request.setAttribute("bookPicture",bookPicture);
        request.setAttribute("categoryName",categoryName);

        request.getRequestDispatcher("details.html").forward(request,response);
    }
}
