package main.web.client;

import main.domain.Category;
import main.domain.Page;
import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : CWQ
 * @Description : 用户界面首页的servlet
 * @date :2018-6-12
 **/
@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        if (method.equalsIgnoreCase("getAll")) {
            getAll(request, response);
        } else if (method.equalsIgnoreCase("listBookWithCategory")) {
            listBookWithCategory(request, response);
        }
    }

    //获取所有图书的列表
    private void getAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BussinessServiceImpl service = new BussinessServiceImpl();
        List<Category> categories = service.getAllCategory();
        request.setAttribute("categories", categories);
        String pagenum = request.getParameter("pagenum");//获取当前页
        Page page = service.getBookPageData(pagenum);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/client/body.jsp").forward(request, response);
    }

    //获取当前分类的图书列表
    private void listBookWithCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BussinessServiceImpl service = new BussinessServiceImpl();
        String categoryID = request.getParameter("categoryID");//获取分类
        List<Category> categories = service.getAllCategory();
        request.setAttribute("categories", categories);
        String pagenum = request.getParameter("pagenum");//获取当前页
        Page page = service.getBookPageData(pagenum, categoryID);
        request.setAttribute("page", page);
        request.getRequestDispatcher("book-display.jsp").forward(request, response);
    }

}
