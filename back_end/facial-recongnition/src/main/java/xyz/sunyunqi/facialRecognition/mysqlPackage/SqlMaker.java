package xyz.sunyunqi.facialRecognition.mysqlPackage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlMaker {

    public static String checkIfSQL(JSONObject res){
        // 检查是否返回结果是否成功
        String user_id = null;
        boolean ifConnect = false;
        int error_code = res.getInt("error_code");
        if (error_code == 0){
            ifConnect = true;
        }
        if (ifConnect) {   // 成功识别人脸，需要返回用户信息
            // 提取用户的user_id
            user_id = res.getJSONObject("result").getJSONArray("user_list")
                    .getJSONObject(0).getString("user_id");
        }
        return user_id;
    }

    public static void queryBySQL(Connection conn, String user_id, JSONObject res){
        Statement stmt;
        // Execute query
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user_info WHERE user_id = " + "\"" + user_id + "\"";
            // get the res from mysql: Execute query formally
            ResultSet resultSet = stmt.executeQuery(sql);

            // iterate the result dataset
            while (resultSet.next()) {
                // temp json
                JSONObject tmpValue = new JSONObject();
                // put it into json
                tmpValue.put("id", resultSet.getString("user_id"));
                tmpValue.put("name", resultSet.getString("user_name"));
                tmpValue.put("age", resultSet.getInt("age"));
                tmpValue.put("role", resultSet.getString("role"));
                tmpValue.put("pic_url", resultSet.getString("pic_url"));
                // use the result to update the res
                res.getJSONObject("result").getJSONArray("user_list").
                        getJSONObject(0).put("user_info", tmpValue);
            }
            // close when completed
            resultSet.close();
            stmt.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public static JSONObject getAllInfo(Connection conn, String user_id){
        JSONObject jsonObject = new JSONObject();
        Statement statement;

        // set the sql statement
        String sql;
        if(user_id == null){
            sql = "SELECT * FROM user_info";
        }else{
            sql = "SELECT * FROM user_info WHERE user_id = " + "\"" + user_id + "\"";
        }

        // try to sql
        JSONArray jsonArray = new JSONArray();
        try{
            statement = conn.createStatement();
            //String sql = "SELECT * FROM user_info"; // 查询指定用户的信息
            ResultSet resultSet = statement.executeQuery(sql);

            // put the result into JsonObjet
            while(resultSet.next()){
                // put info into the temp json
                JSONObject tempObj = new JSONObject();
                tempObj.put("ID",resultSet.getString("user_id"));
                tempObj.put("user_name",resultSet.getString("user_name"));
                tempObj.put("age",resultSet.getInt("age"));
                tempObj.put("gender",resultSet.getString("gender"));
                tempObj.put("role",resultSet.getString("role"));
                tempObj.put("pic_url",resultSet.getString("pic_url"));

                // put it into array
                jsonArray.put(tempObj);
            }

        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        jsonObject.put("user_info",jsonArray);
        return jsonObject;
    }



}
