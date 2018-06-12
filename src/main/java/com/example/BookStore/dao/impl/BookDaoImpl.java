package com.example.BookStore.dao.impl;

import com.example.BookStore.dao.BookDao;
import com.example.BookStore.domain.Book;

import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override

    public void Add(Book book) {

    }

    @Override
    public Book find(int id) {
        return null;
    }

    @Override
    public List<Book> getPageData(int startindex, int pageSize) {
        return null;
    }

    @Override
    public int getTatalRecord() {
        return 0;
    }
}
