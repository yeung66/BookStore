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
        <span class="current">我的购物车</span>
        <span>填写订单</span>
        <span>完成订单</span>
    </div>
    <div class="shopping_cart">
        <li class="f1">商品信息</li>
        <li class="f2">单价（元）</li>
        <li class="f3">数量</li>
        <li class="f4">金额（元）</li>
        <li class="f5">操作</li>
    </div>
    <div class="shopping_list">
        <table width=100% border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <% if(request.getAttribute("cart")!=null && ((Cart)request.getAttribute("cart")).getMap().size()!=0){%>
            <% Map map = ((Cart)request.getAttribute("cart")).getMap();
                Iterator iter = map.keySet().iterator();
                while (iter.hasNext()) {
                Object key = iter.next();
            %>
            <tr name="<%=((CartItem)map.get(key)).getBook().getBookid()%>">
                <td class="choose_one">
                    <input type="checkbox" value="选择" checked="checked">
                </td>
                <td class="row_img">
                    <a>
                        <img src="static/img/book_1.jpg" width="80" height="80">
                    </a>
                </td>
                <td class="row_name">
                    <div class="name">
                        <a href="" target="_blank"><%=((CartItem)map.get(key)).getBook().getBookname()%></a>
                    </div>
                </td>
                <td class="row3">
							<span class="price">
								￥<%=((CartItem)map.get(key)).getBook().getPrice()%>
							</span>
                </td>
                <td class="row3">
							<span class="amount">
								<%--<button>-</button>--%>
								<input data-value="1" value="<%=((CartItem)map.get(key)).getQuantity()%>" type="text" disabled="true">
								<%--<button>+</button>--%>
							</span>
                </td>
                <td class="row4">
							<span class="total_price">
								￥<%=((CartItem)map.get(key)).getPrice()%>
							</span>
                </td>
                <td class="row5">
							<span>
								<button><a href="deleteItem.do?book_id=<%=((CartItem)map.get(key)).getBook().getBookid()%>">删除</a></button>
							</span>
                </td>
            </tr>
            <%}}%>
            <% if(request.getAttribute("cart")==null || ((Cart)request.getAttribute("cart")).getMap().size()==0)
                out.println("<div style='text-align: center;margin-top: 30px;'>购物车还没有物品，快去选购吧！</div>"); %>
            </tbody>
        </table>
    </div>
    <div class="shopping_count">
        <%--<input name="checkbox" type="checkbox" value="全选" checked="unchecked">--%>
        <%--<span style="color:#969696"></span>--%>
        <span class="count">总计（不含运费）：</span>
        <span class="money">￥${requestScope.cart.price}</span>
			<span class="pay">
				<button style="width:75px;height:35px;" id="pay">结算</button>
			</span>
    </div>
</section>

<script>
    var btnPay = document.querySelector('#pay')
    btnPay.addEventListener('click',(e)=>{
        if(document.querySelectorAll('table tbody tr').length>0)
        var http = new XMLHttpRequest()
        http.open('GET','makeOrder.do',true)
        http.onreadystatechange=()=>{
            if(http.readyState==4&&http.status==200){
                alert('结算成功')
                window.location.href='orders.do?userid=${sessionScope.user.userid}'
            }
        }
        http.send()
    })
</script>
</body>
</html>