package main.web.client;

import main.dao.BookDao;
import main.dao.impl.BookDaoImpl;
import main.domain.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DeleteItemServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 1.获取提交的商品的id
        String id = request.getParameter("book_id");

        // 2.从数据库查找是否有此类商品，防止人为输入
        BookDao bookDao = new BookDaoImpl();
        Book book = bookDao.find(id);

        if (book == null)
        {
            // 如果被恶搞跳到购物车中转页面
            request.setAttribute("msg", "商品不存在");
            request.getRequestDispatcher("/cart.html")
                    .forward(request, response);
            return;
        }

// 获取session
        Map<Book, Integer> map = (Map<Book, Integer>) request.getSession().getAttribute("map");

        if (map != null) {

// 3.1 根据id删除商品,因为重写了hashcode()和equals()方法,hashmap底层删除也是依赖的此方法
            Book product = new Book();
            product.setBookid((long)Integer.parseInt(id));

// 3.3 遍历map把主键为product的删除
            map.remove(product);// 删除map根据主键删除，主键就是product
        }
        request.getSession().setAttribute("map", map);
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }
}
