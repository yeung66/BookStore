<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>书籍详情</title>
    <link href="static/css/book-display.css" rel="stylesheet">
</head>
<body>
<nav>

    <div class="logo">
        <a href="index.do?method=listBookWithCategory&pagenum=1&categoryID=1">网上书店</a>
    </div>
    <div class="user">
        <ul>
            <% if(session.getAttribute("admin")==null){%>
            <li>用户：${sessionScope.user.username} </li>
            <li><a href="user.do?method=Logout" id='logout'>注销</a></li>
            <li><a href="Cart.do">购物车</a></li>
            <li><a href="orders.do">我的订单</a></li>
            <% }else {%>
            <li>员工：system</li>
            <li><a href="user.do?method=Logout" id='logout'>注销</a></li>
            <li><a href="manageBook.do?method=list">书籍管理</a></li>
            <li><a href="orders.do">换货管理</a></li>
            <% } %>
        </ul>
    </div>
</nav>

<section class="main">
    <div class="display">
        <div class="head">
            <img src="static/img/book_1.jpg">
            <div class="info" id='${requestScope.bookID}'>
                <p>书名：${requestScope.bookName}</p>
                <p>类别：${requestScope.categoryName}</p>
                <p>价格：${requestScope.price}</p>
                <div>
                    <% if(session.getAttribute("admin")==null){%>
                    <button class="addCart">加入购物车</button>
                    <%}else {%>
                    <button class="deleteBook">下架</button>
                    <% }%>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    var buttonsAdd=document.querySelectorAll('.addCart')
    buttonsAdd.forEach((b)=>{
        b.onclick=(e)=>{
        var http = new XMLHttpRequest()
        http.open('POST','add2Cart.do',true)
        http.setRequestHeader("Content-type","application/x-www-form-urlencoded")
        http.onreadystatechange=function () {
            if(http.status==200 && http.readyState==4){
                alert("添加至购物车成功！")
            }
        }
        http.send('book_id='+e.target.parentNode.parentNode.id)

    }
    })
</script>

</body>
</html>