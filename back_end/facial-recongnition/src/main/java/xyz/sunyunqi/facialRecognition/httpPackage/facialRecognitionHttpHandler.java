package xyz.sunyunqi.facialRecognition.httpPackage;

import com.baidu.aip.face.AipFace;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker;

import java.sql.Connection;
import java.util.HashMap;

import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.getRequest;
import static xyz.sunyunqi.facialRecognition.httpPackage.Controller.responseWeb;
import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.queryBySQL;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.close;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.getConnection;

public class facialRecognitionHttpHandler implements HttpHandler {
    // set APPID/AK/SK
    public static final String APP_ID = "25032156";
    public static final String API_KEY = "iSlTbZlp8qxow3fMgK1jZImU";
    public static final String SECRET_KEY = "yBfwoVYma6ylWpgvj4lh8kEPmLLHCZAE";

    @Override
    public void handle(HttpExchange httpExchange) {
        String image = getRequest(httpExchange);

        // facial recognition
        JSONObject res = facialRecognition(image);
        // System.out.println("识别结果：" + res.toString());

        // check if it is needed to connect to the MySQL
        String user_id = SqlMaker.checkIfSQL(res);

        // try to connect to mysql
        Connection conn = null;
        if(user_id != null){
            // get connection
            conn = getConnection();
            // System.out.println(user_id);
            // sql
            queryBySQL(conn, user_id, res);
            //System.out.println(res);
            // release the connection
            close(conn);
        }

        // final result: res sent to the web
        responseWeb(httpExchange,res);

    }

    /**
     * @author SunYunqi
     * @param image
     * @return JSONObject
     * @description Call Baidu API for face recognition and return the recognition result: JSONObject
     */
    public static JSONObject facialRecognition(String image)
    {
        // initial an ApiFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // call the api
        String imageType = "BASE64";
        String groupIdList = "pig_cat";

        // Pass in optional parameter interface
        HashMap<String, String> options = new HashMap<>();
        options.put("max_face_num", "1");
        options.put("max_user_num", "1");

        // Face recognition and return results
        return client.search(image, imageType, groupIdList, options);
    }


}
