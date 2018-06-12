package com.example.BookStore.dao;

import com.example.BookStore.domain.Book;

import java.util.List;

public interface BookDao {
    /**
     * @Description: 添加书本
     * @Param: Book object
     * @Return: void
     * @Author: CWQ
     * @Date: 2018/6/12
     **/
    void Add(Book book);
    /**
     * @Description: 查找书本
     * @Param: 书本id
     * @Return: Book object
     * @Author: CWQ
     * @Date: 2018/6/12
     **/
    Book find(int id);

    public List<Book> getPageData(int startindex, int pageSize);

    public int getTatalRecord();
}
