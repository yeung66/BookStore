package main.dao;

import main.domain.Book;

import java.util.List;

public interface BookDao {
        /**
         * @Description:
          * @param: book
         * @Return:
         * @Author: CWQ
         * @Date: 2018/6/14
         **/
        void add(Book book);

        Book find(String id);

        public List<Book> getPageData(int startindex, int pagesize);

        public int getTotalRecord();

        public List<Book> getCategoryPageData(int startindex, int pagesize, String type);

        public int getCategoryTotalRecord(String category_id);


}
