package main.dao.impl;

import main.dao.OrderDao;
import main.domain.Book;
import main.domain.Order;
import main.domain.OrderItem;
import main.domain.User;
import main.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;
import java.util.Set;

public class OrderDaoImpl implements OrderDao{
    //添加订单
    public void add(Order order) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into orders(orderID,orderTime,price,state,userID) values(?,?,?,?,?)";
            Object params[] = {order.getOrderID(), order.getOrderTime(), order.getPrice(), order.getState(), order.getUser().getUserid()};
            runner.update(sql, params);
            Set<OrderItem> set = order.getOrderitems();
            for (OrderItem item : set) {
                sql = "insert into orderitem(id,quantity,price,orderID,bookID) values(?,?,?,?,?)";
                params = new Object[]{item.getId(), item.getQuantity(), item.getPrice(), order.getOrderID(), item.getBook().getBookid()};
                runner.update(sql, params);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //查看订单信息
    public Order find(String id){
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from orders where orderID=?";
            Order order = runner.query(sql, new BeanHandler<>(Order.class),id);
            sql = "select * from orderitem where orderID=?";
            List<OrderItem> list = runner.query(sql,  new BeanListHandler<>(OrderItem.class),id);

            for(OrderItem item : list){
                sql = "select book.* from orderitem,book where orderitem.id=? and orderitem.bookID=book.bookID";
                Book book =  runner.query(sql,  new BeanHandler<>(Book.class),item.getId());
                System.out.println(book.getBookname());
                item.setBook(book);
            }
            order.getOrderitems().addAll(list);
            sql = "select users.* from orders,users where orders.orderID=? and orders.userID=users.userID";
            User user = runner.query(sql, new BeanHandler<>(User.class), order.getOrderID());
            order.setUser(user);
            return order;

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //更新订单状态
    public void update(Order order){
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "update order set state=? where orderID=?";
            Object params[] = {order.getState(), order.getOrderID()};
            runner.update(sql, params);
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //查看所有已发货或未发货的订单信息
    public List<Order> getAll(String state){
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from order where state=?";
            List<Order> list = runner.query(sql, new BeanListHandler<>(Order.class), state);
            for(Order order : list){
                sql = "select user.* from order,users where order.orderID=? and order.userID=users.userID";
                User user = runner.query(sql, new BeanHandler<>(User.class),order.getOrderID());
                order.setUser(user);
            }
            return list;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //查看某用户已发货或未发货的订单信息
    public List<Order> getAll(String state, String userid){
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from order where state=? and order.userID=?";
            Object params[] = {state, userid};
            List<Order> list = runner.query(sql, new BeanListHandler<>(Order.class), params);
            for(Order order : list){
                sql = "select * from users where users.userID=?";
                User user = runner.query(sql, new BeanHandler<>(User.class), userid);
                order.setUser(user);
            }
            return list;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //查看某用户的所有订单
    public List<Order> getAllOrder(String userid){
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from orders where userID=?";
            List<Order> list = runner.query(sql, new BeanListHandler<>(Order.class), userid);
            for(Order order : list){
                sql = "select * from users where userID=?";
                User user = runner.query(sql, new BeanHandler<>(User.class),userid);
                order.setUser(user);
            }
            return list;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
