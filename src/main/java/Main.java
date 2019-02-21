import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        Initiator.init();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/project");
        server.start();
    }
}
