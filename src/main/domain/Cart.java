package main.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> map = new HashMap<>();
    private double price;
    //提供把商品添加到购物的功能
    public void addBook2Cart(Book book) {
        //得到对应的购物项
        CartItem cartItem = map.get(book.getBookid().toString());
        //如果是null，说明购物车还没有该购物项
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setBook(book);
            cartItem.setPrice(book.getPrice());
            //把购物项加到购物车中
            map.put(book.getBookid().toString(), cartItem);
        }
        else {
            //如果购物车有该购物项了，那么将购物项的数量+1
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }
    //购物车的价钱是购物项价钱的总和
    public double getPrice() {
        double totalPrice = 0;
        for (Map.Entry<String, CartItem> me : map.entrySet()) {
            CartItem cartItem = me.getValue();
            totalPrice += cartItem.getPrice();
        }
        return totalPrice;
    }

    //提供把商品从购物车删除
    public void deleteBookFromCart(Book book) {
        //得到对应的购物项
        CartItem cartItem = map.get(book.getBookid().toString());
        //从购物车中删除
        map.remove(book.getBookid().toString(),cartItem);
    }
    public Map<String, CartItem> getMap() {
        return map;
    }
    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }
    public void setPrice(double price) {
        this.price = price;
    }

}
