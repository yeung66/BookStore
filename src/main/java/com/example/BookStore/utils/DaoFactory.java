package com.example.BookStore.utils;

/**
 * @author : CWQ
 * @Description : DaoFactory,用于生成Dao
 * @date :2018-06-12
 **/
public class DaoFactory {
    private static final DaoFactory factory = new DaoFactory();
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return factory;
    }

    public <T> T createDao(String className, Class<T> clazz){
        try{
            T t = (T) Class.forName(className).newInstance();
            return t;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
