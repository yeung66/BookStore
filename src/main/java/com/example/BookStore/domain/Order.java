package com.example.BookStore.domain;

public class Order {
  private Long orderid;
  private Long userid;
  private Long bookid;
  private String bookname;
  private Double price;
  private Long quantity;
  private String status;
  private java.sql.Date ordertime;

  public Long getOrderid() {
    return orderid;
  }

  public void setOrderid(Long orderid) {
    this.orderid = orderid;
  }

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public Long getBookid() {
    return bookid;
  }

  public void setBookid(Long bookid) {
    this.bookid = bookid;
  }

  public String getBookname() {
    return bookname;
  }

  public void setBookname(String bookname) {
    this.bookname = bookname;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public java.sql.Date getOrdertime() {
    return ordertime;
  }

  public void setOrdertime(java.sql.Date ordertime) {
    this.ordertime = ordertime;
  }
}
