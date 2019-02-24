import base.Router;
import com.sun.net.httpserver.HttpServer;
import handlers.ProjectListHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        Initiator.init();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        Router router = new Router();
        router.addRoute("/project", new ProjectListHandler());
        server.createContext("/", router);
        server.setExecutor(null);
        server.start();
    }
}
