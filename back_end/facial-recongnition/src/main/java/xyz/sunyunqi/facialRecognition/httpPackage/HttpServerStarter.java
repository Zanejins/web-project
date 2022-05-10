package xyz.sunyunqi.facialRecognition.httpPackage;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * projectName: facialRecognition
 * fileName: HttpSeverStarter
 * packageName: xyz.sunyunqi.facialRecognition
 * date: 2021年11月17日
 */

public class HttpServerStarter {
    public static void main(String[] args) {
        //create a HttpServer instance，banded it to certain IP address and port
        HttpServer httpServer = null;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(8088), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create several HttpContexts，make the path mapped to the certain HttpHandler
        httpServer.createContext("/facialRecognitionServer", new facialRecognitionHttpHandler());
        httpServer.createContext("/QRCodeServer", new HttpToWeChatHandler());
        httpServer.createContext("/LoginSuccessServer", new LoginSuccessHttpHandler());
        httpServer.createContext("/checkSateServer", new CheckStateHttpHandler());
        httpServer.createContext("/infoGetHttpServer", new InfoGetHttpHandler());
        httpServer.createContext("/getCertainInfoServer", new getCertainInfoHttpHandler());
        httpServer.createContext("/getAllContracts", new ContractsInfo());
        httpServer.createContext("/getDetailContract", new DetailContractInfo());
        httpServer.createContext("/sign", new HasSigned());

        // Sets the thread pool object for the server
        httpServer.setExecutor(Executors.newFixedThreadPool(10));

        // start the server
        httpServer.start();
    }
}
