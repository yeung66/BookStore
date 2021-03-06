<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="main.domain.*" %>
<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>购物车</title>
    <link href="static/css/cart.css" rel="stylesheet">
</head>
<body>
<nav>
    <div class="logo">
        <a href="index.do?method=listBookWithCategory&pagenum=1&categoryID=1">网上书店</a>
    </div>
    <div class="user">
        <ul>
            <li>用户：${sessionScope.user.username} </li>
            <li><a href="user.do?method=Logout" id='logout'>注销</a></li>
            <li><a href="#">购物车</a></li>
            <li><a href="orders.do">我的订单</a></li>
        </ul>
    </div>
</nav>

<section class="main">
    <div class="shopping_procedure">
        <span class="current">&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
    </div>
    <div class="shopping_cart">
        <li class="f1">商品信息</li>
        <li class="f2">单价（元）</li>
        <li class="f3">数量</li>
        <li class="f4">金额（元）</li>

    </div>
    <div class="shopping_list">
        <table width=100% border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <%--<% if(request.getAttribute("cart")!=null) {%>--%>
            <% for(OrderItem oi:((Order)request.getAttribute("order")).getOrderitems()){
            %>
            <tr name="<%=oi.getId()%>">

                <td class="row_img">
                    <a>
                        <img src="static/img/book_1.jpg" width="80" height="80">
                    </a>
                </td>
                <td class="row_name">
                    <div class="name">
                        <a href="" target="_blank"><%=oi.getBook().getBookname()%></a>
                    </div>
                </td>
                <td class="row3">
							<span class="price">
								￥<%=oi.getBook().getPrice()%>
							</span>
                </td>
                <td class="row3">
							<span class="amount">
								<%--<button>-</button>--%>
								<input data-value="1" value="<%=oi.getQuantity()%>" type="text" disabled="true">
								<%--<button>+</button>--%>
							</span>
                </td>
                <td class="row4">
							<span class="total_price">
								￥<%=oi.getPrice()%>
							</span>
                </td>
                <td class="row5">
                    <button><a href="book.do?bookID=<%=oi.getBook().getBookid()%>">查看书籍</a></button>
                </td>

            </tr>
            <%}%>

            </tbody>
        </table>
    </div>
    <div class="shopping_count" style="display: flex;justify-content: space-around;">
        <%--<input name="checkbox" type="checkbox" value="全选" checked="unchecked">--%>
        <%--<span style="color:#969696"></span>--%>

        <span>订单日期：${requestScope.order.orderTime}</span>
            <span>订单状态：${requestScope.order.state}</span>
        <span >总计（不含运费）：</span>
        <span >￥${requestScope.order.price}</span>
            <%if(!((Order) request.getAttribute("order")).getState().equals("已收货")){%>
                <button class="Confirm" id="${requestScope.order.orderID}">确认收货</button>
            <%}else{%>
            <button disabled="true">已经收货</button>
            <%}%>
    </div>
</section>

<script>
    var btn =document.querySelector('.Confirm')
    btn.onclick=function (e) {
        var http=new XMLHttpRequest()
        http.open('GET','confirm.do?orderID='+e.target.id,true)
        http.onreadystatechange=function () {
            if(http.status==200&&http.readyState==4){
                alert('收货成功！')
                location.href='orders.do'
            }
        }
        http.send()
    }
</script>
</body>
</html>