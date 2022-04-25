package xyz.sunyunqi.facialRecognition.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {

    private  static JedisPool pool = null;

    static{
        Properties pro = new Properties();
        // get redis's property file
        InputStream inputStream = JedisPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        try {
            pro.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set configuration info
        JedisPoolConfig config = new JedisPoolConfig();
        // turn type object into type, and finally turn it into type int
        config.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));
        config.setMaxIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));
        config.setMaxIdle(Integer.parseInt(pro.get("redis.maxTotal").toString()));

        // create redis buffer pool
        pool = new JedisPool(config, pro.getProperty("redis.ip"), Integer.parseInt(pro.get("redis.port").toString()));
    }

    public static Jedis getJedis(){

        return pool.getResource();
    }

    public static void release(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
