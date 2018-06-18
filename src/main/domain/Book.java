package main.domain;

public class Book {
    private String bookid;
    private String bookname;
    private Double price;
    private String categoryID;
    private String bookpicture;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
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

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getBookpicture() {
        return bookpicture;
    }

    public void setBookpicture(String bookpicture) {
        this.bookpicture = bookpicture;
    }
}
