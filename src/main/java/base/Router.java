package base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Router implements HttpHandler {
    private ArrayList<Route> routes = new ArrayList<>();

    public void addRoute(String path, HttpHandler handler) {
        routes.add(new Route(path, handler));
    }

    public void handle(HttpExchange t) throws IOException {
        boolean found = false;
        HttpHandler handler = null;

        String[] pathParts = Route.getPathParts(t.getRequestURI().getPath());
        for (Route route: routes) {
            boolean check = true;
            String[] routeParts = route.getPathParts();
            if (pathParts.length != routeParts.length) {
                continue;
            }
            for (int i = 0; i < pathParts.length; i++) {
                if (routeParts[i].startsWith("/")) {  // param
                    continue;
                }
                if (!pathParts[i].equals(routeParts[i])) {
                    check = false;
                }
            }
            if (check) {
                found = true;
                handler = route.getHandler();
                break;
            }
        }
        if (found && handler != null) {
            handler.handle(t);
        } else {
            String response = "Not Found";
            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
