package xyz.sunyunqi.facialRecognition.httpPackage;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.Connection;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.getRequest;
import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.responseWeb;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.insertNewContract;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.getConnection;

/**
 * TODO
 *
 * @author 孙蕴琦
 * @version 1.0
 * @className: uploadtemplate
 * @description TODO
 * @date 2022/6/5 22:15
 */
public class uploadtemplate implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // 获得post请求的内容
        String info = getRequest(exchange);

        // 提取出info中的contract_id，将其状态修改为已签署
        org.json.JSONObject jsonObject = uploadNewTemplate(info);

        // 返回前端
        responseWeb(exchange, jsonObject);
    }

    public static org.json.JSONObject uploadNewTemplate(String info) {
        // string to json
        JSONObject jsonObject = JSONObject.parseObject(info);

        // get two pieces of information from the jsonObject: user_id, uuid
        String type = jsonObject.get("type").toString();
        String new_url = jsonObject.get("URL").toString();
        String template_name = jsonObject.get("template_name").toString();
        // get a conn
        Connection conn = getConnection();

        // SQL and return
        return insertNewContract(conn, type, new_url, template_name);
    }
}
