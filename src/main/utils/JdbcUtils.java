package main.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author : CWQ
 * @Description : JdbcUtils，用于生成数据库连接
 * @date :2018-06-12
 **/
public class JdbcUtils {
    private static DataSource dataSource = null;
    static{
        dataSource = new ComboPooledDataSource();
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
