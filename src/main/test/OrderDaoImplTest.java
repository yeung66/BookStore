package main.test;

import junit.framework.TestCase;
import main.dao.OrderDao;
import main.dao.impl.OrderDaoImpl;
import main.domain.Order;

public class OrderDaoImplTest extends TestCase {
    public void testAdd() throws Exception {
        String orderID = "74770874-72e1-4a8e-94ab-87fb9dfbc7ea";
        OrderDao orderDao = new OrderDaoImpl();
        Order order = orderDao.find(orderID);
        System.out.println(order.getPrice());
         System.out.println(order.getUser().getUserid());
    }

}