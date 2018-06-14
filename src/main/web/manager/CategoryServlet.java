package main.web.manager;

import main.domain.Category;
import main.service.BussinessService;
import main.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *@Description : 管理员操作分类的servlet
 *@author : CWQ
 *@date :2018-6-12
 **/
@WebServlet(name = "CategoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listAll(request,response);
    }
    /**
     * @Description: 列出所有分类
      * @param :null
     * @Return:
     * @Author: CWQ
     * @Date: 2018/6/14
     **/
    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BussinessServiceImpl service = new BussinessServiceImpl();
        List<Category> CategoryList = service.getAllCategory();
        request.setAttribute("categories", CategoryList);
        request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
    }
}
