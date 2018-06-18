package main.web.client;

import main.domain.User;
import main.service.impl.UserService;
import main.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
* @Author: Jinglin Chen
* @Description: UserServlet
* @Date: 10:01 2018/6/14
*/
public class UserServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        UserService service = new UserService();
        if (method.equals("login")) {
            try { //得到页面传递过来的数据
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                User user = service.loginUser(username, password);
                if(user==null) throw new Exception();
                request.getSession().setAttribute("user", user);
                //request.getRequestDispatcher("book-display.html").forward(request, response);
                response.sendRedirect("index.do?method=listBookWithCategory&pagenum=1&categoryID=1");
            } catch (Exception e) {
                request.setAttribute("message", "登陆失败了！");
                request.getRequestDispatcher("Login failed.html").forward(request, response);
            }
        } else if (method.equals("register")) {
            try {
                //得到JSP传递过来的数据，封装成Bean对象
//                User user = WebUtils.request2Bean(request, User.class);
                User user = new User();
                String userName = request.getParameter("username");
                String pw = request.getParameter("password");
                user.setPw(pw);
                user.setUsername(userName);
                user.setUserid(WebUtils.makeID());
                service.registerUser(user);
                request.setAttribute("message", "注册成功了！");response.getWriter().println("<script>alert('Register Success!')</script>");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "注册失败了！");response.getWriter().println("<script>alert('Fail to register!')</script>");
            }

        } else if (method.equals("Logout")) {
            //销毁session
            request.getSession().removeAttribute("cart");
            request.getSession().invalidate();
            //回到首页
            request.getRequestDispatcher("Sign in.html").forward(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
