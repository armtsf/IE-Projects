import base.Router;
import com.sun.net.httpserver.HttpServer;
import handlers.ProjectDetailsHandler;
import handlers.ProjectListHandler;
import handlers.UserDetailsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        Initiator.init();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        Router router = new Router();
        router.addRoute("/project", new ProjectListHandler());
        router.addRoute("/project/{id}", new ProjectDetailsHandler());
        router.addRoute("/user/{id}", new UserDetailsHandler());
        server.createContext("/", router);
        server.setExecutor(null);
        server.start();
    }
}
