package main.dao;

import main.domain.Order;

import java.util.List;

public interface OrderDao {
    void add(Order order);
    Order find(String id);
    void update(Order order);
    List<Order> getAll(boolean state);
    List<Order> getAll(boolean state, String userid);
    List<Order> getAllOrder(String userid);
}
