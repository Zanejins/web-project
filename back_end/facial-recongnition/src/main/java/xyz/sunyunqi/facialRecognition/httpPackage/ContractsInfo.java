package xyz.sunyunqi.facialRecognition.httpPackage;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.*;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.getAllContracts;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.getConnection;

/**
 * TODO
 *
 * @author 孙蕴琦
 * @version 1.0
 * @className: ContractsInfo
 * @description TODO
 * @date 2022/5/9 19:43
 */
public class ContractsInfo implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // get info from web ...
        String paramStr = exchange.getRequestURI().getQuery();
        Map<String, String> info = getParams(paramStr);
        String user_id = info.get("user_id");

        // turn the info into json and get the user_id
        org.json.JSONObject jsonObject = getContracts(user_id);

        // response
        responseWeb(exchange, jsonObject);
    }

    public static org.json.JSONObject getContracts(String user_id) {
        // get a conn
        Connection conn = getConnection();

        // SQL and return
        return getAllContracts(conn, user_id);
    }
}
