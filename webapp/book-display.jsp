<%@ page import="java.util.List" %>
<%@ page import="main.domain.Category" %>
<%@ page import="main.domain.Book" %>
<%@ page import="main.domain.Page" %>
<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>书籍页面</title>
    <link href="static/css/book-display.css" rel="stylesheet">
</head>
<body>
<nav>

    <div class="logo">
        <a href="#">网上书店</a>
    </div>
    <div class="user">
        <ul>
            <li>用户：${sessionScope.user.username} </li>
            <li><a href="user.do?method=Logout" id='logout'>注销</a></li>
            <li><a href="#">购物车</a></li>
            <li><a href="#">我的订单</a></li>
        </ul>
    </div>
</nav>

<section class="main">
    <form class="search">
        <input type="text" placeholder="请输入想找的书">
        <input type="submit" value="搜索">
    </form>
    <div class="display">
        <header>
            <ul>
                <%--<li id="oncatagory"><a href="#">分类</a></li>--%>
                <%--<li><a href="#">分类</a></li>--%>
                <%--<li><a href="#">分类</a></li>--%>
                <%--<li><a href="#">分类</a></li>--%>
                <%--<li><a href="#">分类</a></li>--%>
                <%--<li><a href="#">分类</a></li>--%>
                <% for(Category c:(List<Category>)request.getAttribute("categories") ){%>
                    <% String i =request.getParameter("categoryID").equals(c.getId())?"id=\"oncatagory\"":"";%>
                    <%="<li " + i+
                            "><a href='index.do?pagenum=1&method=listBookWithCategory&categoryID="+
                            c.getId()+"'>"+c.getName()+"</a></li>"%>
                <% } %>
            </ul>
        </header>
        <div class="books">
            <%--<div class="book">--%>
                <%--<a><img></a>--%>
                <%--<div class="title">书名</div>--%>
                <%--<div class="price">价格</div>--%>
                <%--<div class="button">--%>
                    <%--<button>加入购物车</button>--%>
                    <%--<button>直接购买</button>--%>
                <%--</div>--%>
            <%--</div>--%>
            <% for(Object b:((Page)request.getAttribute("page")).getList()){

            %>
                <%="<div class=\"book\" id='" +((Book) b).getBookid()+
                        "'>\n" +
                        "                <a><img></a>\n" +
                        "                <div class=\"title\">" +((Book) b).getBookname()+
                        "</div>\n" +
                        "                <div class=\"price\">" +((Book) b).getPrice()+
                        "</div>\n" +
                        "                <div class=\"button\">\n" +
                        "                    <button>加入购物车</button>\n" +
                        "                    <button>直接购买</button>\n" +
                        "                </div>\n" +
                        "            </div>"%>
            <%}%>
        </div>
</section>

</body>
</html>