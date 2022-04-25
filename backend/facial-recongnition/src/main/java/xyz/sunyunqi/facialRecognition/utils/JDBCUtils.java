package xyz.sunyunqi.facialRecognition.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DruidDataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            //通过当前类的类加载器，读取jdbc.properties配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建了数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            //测试链接是否成功
            //System.out.println(dataSource.getConnection());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * 如果返回null，说明获取链接失败，有值就是获取连接成功
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
    public static void close(Connection conn){
        if(conn != null)
        {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
