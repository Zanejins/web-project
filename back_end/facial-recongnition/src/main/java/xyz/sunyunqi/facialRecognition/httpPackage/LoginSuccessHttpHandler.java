package xyz.sunyunqi.facialRecognition.httpPackage;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import redis.clients.jedis.Jedis;
import xyz.sunyunqi.facialRecognition.utils.JedisPoolUtils;

import com.alibaba.fastjson.JSONObject;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.getRequest;
import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.responseWeb;

public class LoginSuccessHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange){
        // read the request
        String info = getRequest(httpExchange);
        // set the uuid in redis
        boolean ifKeyExists = redisEnded(info);
        // get return info
        org.json.JSONObject jsonObject = retInfo(ifKeyExists);
        // can return some information to the web to show it is successfully set in redis
        responseWeb(httpExchange, jsonObject);

    }
    public static boolean redisEnded(String info){
        // string to json
        JSONObject jsonObject = JSONObject.parseObject(info);

        // get two pieces of information from the jsonObject: user_id, uuid
        String user_id = jsonObject.get("uid").toString();
        String uuid = jsonObject.get("id").toString();

        // get connect to the redis
        Jedis jedis = JedisPoolUtils.getJedis();

        // check the key's existence
        boolean ifKeyExists = jedis.exists("state"+uuid);

        // if the key exist in redis
        if(ifKeyExists){
            // modify the user's state
            jedis.set("state"+uuid, user_id);
        }

        // release the connection
        JedisPoolUtils.release(jedis);
        return ifKeyExists;
    }

    public static org.json.JSONObject retInfo(boolean existence){
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        if(existence){
            jsonObject.put("error_code",1);
            jsonObject.put("description", "UPDATE SUCCESSFULLY");
        }else{
            jsonObject.put("error_code",0);
            jsonObject.put("description", "INVALID UUID");
        }
        return jsonObject;
    }

}
