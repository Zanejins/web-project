package xyz.sunyunqi.facialRecognition.httpPackage;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.responseWeb;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.getAllInfo;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.close;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.getConnection;

public class InfoGetHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // get the request from web ...

        // get the information
        JSONObject jsonObject = retInfo();
        // return the info to web
        responseWeb(httpExchange, jsonObject);

    }

    public static JSONObject retInfo(){
        // get conn from the pool
        Connection conn = getConnection();
        // get info
        JSONObject jsonObject = getAllInfo(conn, null);
        // close connection
        close(conn);
        // return to handler
        return jsonObject;
    }

}
