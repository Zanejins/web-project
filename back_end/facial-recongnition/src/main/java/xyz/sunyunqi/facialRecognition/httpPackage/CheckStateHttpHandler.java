package xyz.sunyunqi.facialRecognition.httpPackage;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import xyz.sunyunqi.facialRecognition.utils.JedisPoolUtils;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.getRequest;
import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.responseWeb;

public class CheckStateHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange){
        // read the request
        String info = getRequest(httpExchange);

        // get the user's state
        JSONObject jsonObject = getState(info);

        // return to the web
        responseWeb(httpExchange, jsonObject);
    }

    public static JSONObject getState(String info){
        // get the id from web info
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(info);
        String uuid = jsonObject.get("id").toString();
        Jedis jedis = JedisPoolUtils.getJedis();
        String id = jedis.get("state" + uuid);

        // prepare three para
        String errorCode;
        String state;
        String uid;

        // match the state depends on the id
        if ("0".equals(id)||id==null){ // if it didn't log in or the id can't be got
            state = "未登录";
            errorCode = "0";
            uid = "0";
        }else{  // log in
            state = "已登录";
            errorCode = "1";
            uid = id;
        }

        // return the state of our action
        JSONObject resJson = new JSONObject();
        resJson.put("error_code",errorCode);
        resJson.put("state",state);
        resJson.put("uid",uid);
        return resJson;
    }
}
