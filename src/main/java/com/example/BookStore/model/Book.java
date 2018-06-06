package com.example.BookStore.model;

public class Book {
  private Long bookid;
  private String bookname;
  private Double price;
  private String type;
  private String bookpicture;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBookpicture() {
    return bookpicture;
  }

  public void setBookpicture(String bookpicture) {
    this.bookpicture = bookpicture;
  }
}
