package xyz.sunyunqi.facialRecognition.httpPackage;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.*;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.getAllContracts;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.getDetailContractInfo;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.getConnection;

/**
 * TODO
 *
 * @author 孙蕴琦
 * @version 1.0
 * @className: DetialContractInfo
 * @description TODO
 * @date 2022/5/9 22:09
 */
public class DetailContractInfo implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // get info from web ...
        String paramStr = httpExchange.getRequestURI().getQuery();
        Map<String, String> info = getParams(paramStr);
        String contract_id = info.get("contract_id");

        // turn the info into json and get the user_id
        org.json.JSONObject jsonObject = getDetail(contract_id);

        // response
        responseWeb(httpExchange, jsonObject);
    }

    public static org.json.JSONObject getDetail(String contract_id) {
        // get a conn
        Connection conn = getConnection();

        // SQL and return
        return getDetailContractInfo(conn, contract_id);
    }
}
