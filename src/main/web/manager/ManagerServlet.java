package main.web.manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Author: CWQ
 * @Description: ManagerServlet 管理员登陆操作的servlet
 * @Date: 10:01 2018/6/18
 */
@WebServlet(name = "ManagerServlet")
public class ManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ManagerName = request.getParameter("ManagerName");
        String password = request.getParameter("password");
        if(ManagerName.equals("system")&&password.equals("123456")){
            request.getRequestDispatcher("Manager.jsp").forward(request,response);
        }else {
            request.setAttribute("errorMessage","用户名和密码错误，请重新输入：");
            request.getRequestDispatcher("Login failed.html").forward(request,response);
        }
    }
}
