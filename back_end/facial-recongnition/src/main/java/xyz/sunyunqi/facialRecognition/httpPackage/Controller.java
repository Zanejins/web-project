package xyz.sunyunqi.facialRecognition.httpPackage;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 孙蕴琦
 * @description provide two methods for HttpServer to get the JSONObject from the web and return the result of HttpServer's handling
 * @date 2020.11.17
 * @version 1.0
 */

public class Controller {
    /**
     * @param httpExchange
     * @return String , a String which parse from JSONObject coming from web
     */
    public static String getRequest(HttpExchange httpExchange){
        BufferedReader bufferedReader = null;
        StringBuilder requestBodyContent = new StringBuilder();
        String line;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), "utf-8"));     // 读取请求
            while ((line = bufferedReader.readLine()) != null) {
                requestBodyContent.append(line);    // 请求存入requestBodyContent中
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return requestBodyContent.toString();    // StringBuilder转成String类型
    }


    public static Map<String, String> getParams(String paramStr){
        if(paramStr != null) {
            return Stream.of(paramStr.split("&"))
                    .filter(s -> s.split("=").length == 2 && s.split("=")[0].length() > 0 && s.split("=")[1].length() > 0)
                    .collect(Collectors.toMap(x -> x.toString().split("=")[0], y -> y.toString().split("=")[1]));
        }
        return null;
    }

    /**
     *
     * @param httpExchange
     * @param jsonObject
     */
    public static void responseWeb(HttpExchange httpExchange, JSONObject jsonObject){
        // json to String
        String res = jsonObject.toString();
        // return to the web
        byte[] responseContentByte = res.getBytes(StandardCharsets.UTF_8);
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        try {
            httpExchange.sendResponseHeaders(200, responseContentByte.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream out = httpExchange.getResponseBody();
        try {
            out.write(responseContentByte);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
