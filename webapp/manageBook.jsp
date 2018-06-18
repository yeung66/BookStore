<%@ page import="main.domain.Page" %>
<%@ page import="main.domain.Book" %>
<%@ page import="main.domain.Category" %>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>书籍上下架</title>
    <link href="static/css/book-manage.css" rel="stylesheet">
</head>
<body>
<nav>

    <div class="logo">
        <a href="#">网上书店</a>
    </div>
    <div class="user">
        <ul>
            <li>员工：System </li>
            <li><a href="user.do?method=Logout" id='logout'>注销</a></li>
            <li><a href="#">书籍管理</a></li>
            <li><a href="#">退换货管理</a></li>
        </ul>
    </div>
</nav>

<section class="main">
    <form class="search">
        <p class="result">书籍列表</p>
    </form>
    <div class="display">
        <div class="books">

            <% for(Object b:((Page)request.getAttribute("page")).getList()){

            %>
            <%="<div class=\"book\" id='" +((Book) b).getBookid()+
                    "'>\n" +
                    "                <a href='book.do?bookID=" +((Book) b).getBookid()+
                    "'><img src=\"static/img/book_1.jpg\"></a>\n" +
                    "                <div class=\"title\">" +((Book) b).getBookname()+
                    "</div>\n" +
                    "                <div class=\"price\">" +((Book) b).getPrice()+
                    "</div>\n" +
                    "                <div class=\"button\">\n" +
                    "                    <button class='addCart'>下架</button>\n" +
//                        "                    <button class='buyCart'>直接购买</button>\n" +
                    "                </div>\n" +
                    "            </div>"%>
            <%}%>
        </div>

        </div>
        </div>
        <form class="search">
            <p class="result">添加书籍</p>
        </form>
        <div class="orders">
            <div class="order">
                <a><img></a>
                <div class="title">书名：</div>
                <input type="text" class="text1">
                <div class="title">类别：</div>
                <select class="option">
                    <% for(Category c:(List<Category>)request.getAttribute("categories") ){%>
                    <option value="<%=c.getName()%>"><%=c.getName()%></option>

                    <% } %>
                </select>
                <div class="price">价格：</div>
                <input type="text" class="text1">
                <div class="button">
                    <button>确认上架</button>
                </div>
            </div>

</section>
<script>
    var buttonsAdd=document.querySelectorAll('.addCart')
    buttonsAdd.forEach((b)=>{
        b.onclick=(e)=>{
        var http = new XMLHttpRequest()
        http.open('POST','manageBook.do',true)
        http.setRequestHeader("Content-type","application/x-www-form-urlencoded")
        http.onreadystatechange=function () {
            if(http.status==200 && http.readyState==4){
                alert("下架成功！")
                location.reload()
            }
        }
        http.send('bookID='+e.target.parentNode.parentNode.id+'&method=delete')

    }
    })
</script>
</body>
</html>