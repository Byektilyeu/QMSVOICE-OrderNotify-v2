import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONException;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

    //main function
    public static void main(String[] args) throws Exception {
        //server-iin port
        int port = 8011;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new Myhandler());
        server.setExecutor(null);
        server.start();
        System.out.println("server running at port " + port);
        dbConnection database = new dbConnection();
        database.createNewDb();
        database.createNewTable();
        new GUI();
    }

    static class Myhandler implements HttpHandler {
        char[] xmlData = new char[0];
        @Override
        public void handle(HttpExchange t) throws IOException {
            if ("POST".equals(t.getRequestMethod())) {
                InputStream inStream = t.getRequestBody();
                int lenght = inStream.available();
                xmlData = new char[lenght];
                final StringBuilder out = new StringBuilder();
                Reader in = new InputStreamReader(inStream, "UTF-8");
                try {
                    for (; ; ) {
                        int rsz = in.read(xmlData, 0, xmlData.length);
                        if (rsz < 0)
                            break;
                        out.append(xmlData, 0, rsz);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                String responseS = inStream.toString();
                t.sendResponseHeaders(200, responseS.length());
                OutputStream os = t.getResponseBody();
                os.write(responseS.getBytes());
                os.close();

            } else {
                String response = "POST method-oor request yvuulna uu";
                t.sendResponseHeaders(404, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
            }

            mainClass mainclass = new mainClass();

            try {
                mainclass.getResponseData(xmlData);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



