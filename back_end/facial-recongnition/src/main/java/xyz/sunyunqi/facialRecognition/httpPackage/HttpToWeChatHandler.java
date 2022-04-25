package xyz.sunyunqi.facialRecognition.httpPackage;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import xyz.sunyunqi.facialRecognition.utils.JedisPoolUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.responseWeb;

public class HttpToWeChatHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange){
        // start the redis
        UUID uuid = RedisStarter();
        // get the token
        String token = getQRToken();
        // get the QRCode
        String responseContentStr = getQRCode(token,uuid);
        // package the results
        JSONObject jsonObject = packageIntoJson(responseContentStr, uuid);
        // final result: res sent to the web
        responseWeb(httpExchange,jsonObject);

    }

    public static UUID RedisStarter(){
        Jedis jedis = JedisPoolUtils.getJedis();    // get a jedis instance
        UUID uuid = UUID.randomUUID();              // get a uuid
        jedis.set("state" + uuid , "0");            // set the user's state
        JedisPoolUtils.release(jedis);
        return uuid;
    }

    public static String getQRToken(){
        String apiKey = "wx626bb85ae061201d";
        String apiSecret = "";
        String requestURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + apiKey + "&secret=" + apiSecret;
        URL url1;
        try {
            url1 = new URL(requestURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder().url(requestURL).method("GET", null).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseContentStr = null;
        try {
            responseContentStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject(responseContentStr);
        String token = json.getString("access_token");
        return token;
    }

    public static String getQRCode(String token, UUID uuid){
        URL url = null;
        try {
            url = new URL("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+token);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        JSONObject json = new JSONObject("{\n    \"path\": \"?uuid=" + uuid + "\"\n}");
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json.toString());
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        InputStream resStream = null;
        byte[] bytes = new byte[0];
        try {
            response = client.newCall(request).execute();
            resStream = response.body().byteStream();
            bytes = IOUtils.toByteArray(resStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    public static JSONObject packageIntoJson(String responseContentStr, UUID uuid){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("image",responseContentStr);
        jsonObject.put("id",uuid);
        return jsonObject;
    }


}
