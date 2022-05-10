package xyz.sunyunqi.facialRecognition.httpPackage;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.Connection;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.getRequest;
import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.responseWeb;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.getAllInfo;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.getConnection;

public class getCertainInfoHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // get info from web
        String info = getRequest(httpExchange);
        // turn the info into json and get the user_id
        org.json.JSONObject jsonObject = getUserInfo(info);
        // response the web
        responseWeb(httpExchange, jsonObject);
    }

    public static org.json.JSONObject getUserInfo(String info){
        // string to json
        JSONObject jsonObject = JSONObject.parseObject(info);

        // get two pieces of information from the jsonObject: user_id, uuid
        String user_id = jsonObject.get("user_id").toString();

        // get a conn
        Connection conn = getConnection();

        // SQL and return
        return getAllInfo(conn, user_id);
    }
}
