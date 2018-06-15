package main.web.manager;

import main.domain.Book;
import main.domain.Category;
import main.domain.Page;
import main.service.impl.BussinessServiceImpl;
import main.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author : CWQ
 * @Description : 管理员书籍相关操作的servlet，包含添加书籍、列出所有书籍
 * @date :2018-6-12
 **/
@WebServlet(name = "BookServlet")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equalsIgnoreCase("addUI")) {
            addUI(request, response);
        }
        if (method.equalsIgnoreCase("add")) {
            add(request, response);
        }
        if (method.equalsIgnoreCase("list")) {
            list(request, response);
        }
    }
    //获取书籍列表
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagenum = request.getParameter("pagenum");
        BussinessServiceImpl service = new BussinessServiceImpl();
        Page page = service.getBookPageData(pagenum);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
    }
    //转到添加book的界面
    private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BusinessServiceImpl service = new BusinessServiceImpl();
        List<Category> category = service.getAllCategory();
        request.setAttribute("categories", category);
        request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
    }
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Book book = upLoadData(request);
            BussinessServiceImpl service = new BussinessServiceImpl();
            book.setBookid(Long.valueOf(UUIDUtils.makeID()));
            service.addBook(book);
            request.setAttribute("message", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
    //上传图片数据
    private Book upLoadData(HttpServletRequest request) {
        Book book = new Book();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    BeanUtils.setProperty(book, name, value);
                } else {
                    String filename = item.getName();
                    String savefilename = makeFileName(filename);
                    String savepath = this.getServletContext().getRealPath("/images");
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
                    int len = 0;
                    byte buffer[] = new byte[1024];
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    book.setBookpicture(savefilename);
                    System.out.println(book.getBookpicture());//test
                }
            }
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //生成图片的文件名
    public String makeFileName(String filename) {
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }



}

