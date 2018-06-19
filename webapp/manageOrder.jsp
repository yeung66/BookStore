<%@ page import="java.util.List" %>
<%@ page import="main.domain.Category" %>
<%@ page import="main.domain.Book" %>
<%@ page import="main.domain.Page" %>
<%@ page import="main.domain.Order" %>
<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>书籍页面</title>
    <link href="static/css/book-display.css" rel="stylesheet">
    <style>
        .orders{
            display: flex;
            flex-direction: column;
            margin: 3%;
        }

        .orders .row {
            display: flex;
            justify-content: space-around;
            padding: 20px 0;
        }
        .orders .row > * {
            flex: 1;
            text-align: center;
        }
    </style>
</head>
<body>
<nav>

    <div class="logo">
        <a href="manageBook.do?method=list">网上书店</a>
    </div>
    <div class="user">
        <ul>
            <li>员工：system </li>
            <li><a href="user.do?method=Logout" id='logout'>注销</a></li>
            <li><a href="manageBook.do?method=list">书籍管理</a></li>
            <li><a href="#">订单管理</a></li>
        </ul>
    </div>
</nav>

<section class="main">

    <div class="display">
        <header>
            <ul>
                <% String[] arr = {"待发货","待收货","已收货"};
                    for(String state:arr){%>
                <li
                        <% if(state.equals(request.getParameter("state"))){%>id="oncatagory"<%}%>
                ><a href="manageOrder.do?state=<%=state%>"><%=state%></a></li>
                <% } %>


            </ul>
        </header>
        <div class="orders">
            <div class="row">
                <span>订单时间</span>
                <span>订单用户</span>
                <span>订单总价</span>
                <span>查看详情</span>
            </div>

            <%for(Order o:(List<Order>)request.getAttribute("orders")){%>
            <div class="row">
                <span><%=o.getOrderTime()%></span>
                <span><%=o.getUser().getUsername()%></span>
                <span><%=o.getPrice()%></span>
                <span><button><a href="manageOrderDetail.do?orderID=<%=o.getOrderID()%>">查看详情</a></button></span>
            </div>
            <%}%>
        </div>

    </div>
</section>

</body>
</html>