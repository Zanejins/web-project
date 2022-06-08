package xyz.sunyunqi.facialRecognition.mysqlPackage;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.json.JSONObject;

import java.sql.Connection;

import static xyz.sunyunqi.facialRecognition.mysqlPackage.SqlMaker.*;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.close;
import static xyz.sunyunqi.facialRecognition.utils.JDBCUtils.getConnection;

public class SqlMakerTest extends TestCase {


    //获取所有用户信息
    public void testGetAllInfo() {
        Connection conn = getConnection();
        // get info
        JSONObject jsonObject = getAllInfo(conn, null);
        // close connection
        close(conn);
        System.out.println("获取所有用户信息：");
        System.out.println(jsonObject.getJSONArray("user_info"));
    }

    //根据Id查询合同信息
    public void testGetAllContracts() {
        Connection conn = getConnection();
        JSONObject allContracts = getAllContracts(conn, "0x000002");
        close(conn);
        System.out.println("id为0x000002的用户的所有合同为：");
        System.out.println(allContracts);
    }

    public void testGetDetailContractInfo() {
        Connection conn = getConnection();
        String contracts_id="2";
        JSONObject object = getDetailContractInfo(conn, contracts_id);
        close(conn);
        System.out.println("查询到id为2的合同详情信息为：");
        System.out.println(object);
    }

    public void testModifyHas_sign() {
        String contract_id="1";
        String URL="http://image.xinwuyun.cloud/2022-06-07 14:47:12.196388signedPdf.pdf";
        Connection conn = getConnection();

        // SQL and return
        System.out.println("改变状态为已签署："+ modifyHas_sign(conn, contract_id, URL));

        close(conn);
    }


}