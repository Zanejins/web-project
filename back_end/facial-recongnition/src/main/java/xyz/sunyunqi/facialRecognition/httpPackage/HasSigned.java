package xyz.sunyunqi.facialRecognition.httpPackage;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.getRequest;
import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.responseWeb;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.getAllInfo;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.modifyHas_sign;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.getConnection;

import java.io.IOException;
import java.sql.Connection;

/**
 * TODO
 *
 * @author 孙蕴琦
 * @version 1.0
 * @className: HasSigned
 * @description TODO
 * @date 2022/5/10 8:53
 */
public class HasSigned implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // 获得post请求的内容
        String info = getRequest(httpExchange);

        // 提取出info中的contract_id，将其状态修改为已签署
        org.json.JSONObject jsonObject = modifyContractStatus(info);

        // 返回前端
        responseWeb(httpExchange, jsonObject);

    }

    public static org.json.JSONObject modifyContractStatus(String info) {
        // string to json
        JSONObject jsonObject = JSONObject.parseObject(info);

        // get two pieces of information from the jsonObject: user_id, uuid
        String contract_id = jsonObject.get("contract_id").toString();
        String new_url = jsonObject.get("URL").toString();

        // get a conn
        Connection conn = getConnection();

        // SQL and return
        return modifyHas_sign(conn, contract_id, new_url);
    }
}
