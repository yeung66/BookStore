package main.web.client;

import main.dao.impl.CategoryDaoImpl;
import main.domain.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "testServlet")
public class testServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        Category category = categoryDao.findCategory("1");
        request.setAttribute("categoryName",category.getName());
        request.getRequestDispatcher("index.jsp").forward(request,response);
        System.out.println(category.getName());
    }
}
