<%@ page import="main.domain.Order" %>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>订单管理</title>
    <link href="static/css/order.css" rel="stylesheet">
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
            <li><a href="Cart.do">购物车</a></li>
            <li><a href="#">我的订单</a></li>
        </ul>
    </div>
</nav>
<section class="main">
    <header>
        <ul>
            <!-- <li id="oncatagory"><a href="#">全部</a></li>
            <li><a href="#">待付款</a></li>
            <li><a href="#">待发货</a></li>
            <li><a href="#">待收货</a></li>
            <li><a href="#">待评价</a></li> -->
            <li>我的订单情况</li>
        </ul>
    </header>
    <div class="orders" style="flex-wrap: wrap;">
        <div class="order">
            <div class="time">订单时间</div>
            <div>订单状态</div>
            <div>订单总价</div>
            <div>操作</div>

        </div>
        <% for(Order o:(List<Order>)request.getAttribute("orders")){%>
        <div class="order">
            <div class="time"><%=o.getOrdertime()%></div>
            <div><%=o.isState()%></div>
            <div><%=o.getPrice()%></div>
            <div><button><a href="orderDetail.do?<%=o.getId()%>">查看详情</a></button></div>

        </div>

        <%}%>
    </div>
</section>
</body>
</html>