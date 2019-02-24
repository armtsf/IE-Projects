package base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import handlers.ServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Router implements HttpHandler {
    private ArrayList<Route> routes = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    public void addRoute(String path, ServiceHandler handler) {
        routes.add(new Route(path, handler));
    }

    public void handle(HttpExchange t) throws IOException {
        boolean found = false;
        ServiceHandler handler = null;

        String path = t.getRequestURI().getPath();
        String[] pathParts = Route.getPathParts(path);
        for (Route route: routes) {
            boolean check = true;
            String[] routeParts = route.getPathParts();
            if (pathParts.length != routeParts.length) {
                continue;
            }
            for (int i = 0; i < pathParts.length; i++) {
                if (routeParts[i].startsWith("{")) {  // param
                    continue;
                }
                if (!pathParts[i].equals(routeParts[i])) {
                    check = false;
                }
            }
            if (check) {
                found = true;
                handler = route.getHandler();
                handler.setUriVars(Route.getRouteVars(route, path));
                break;
            }
        }
        if (found) {
            handler.handle(t);
        } else {
            String response = "Not Found";
            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        logger.info(t.getRequestMethod() + " " + t.getRequestURI() + " "+ t.getResponseCode());
    }
}
