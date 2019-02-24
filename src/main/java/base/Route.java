package base;

import handlers.ServiceHandler;

import java.io.InvalidObjectException;
import java.util.HashMap;

public class Route {
    private String path;
    private ServiceHandler handler;
    private String[] pathParts;

    public Route(String path, ServiceHandler handler) {
        this.handler = handler;
        this.path = path;
        this.pathParts = getPathParts(path);
    }

    public ServiceHandler getHandler() {
        return handler;
    }

    public String getPath() {
        return path;
    }

    public String[] getPathParts() {
        return pathParts;
    }

    public static String[] getPathParts(String p) {
        if (p.startsWith("/")) {
            p = p.substring(1);
        }
        return p.split("/");
    }

    public static HashMap<String, String> getRouteVars(Route route, String path) {
        HashMap<String, String> res = new HashMap<>();

        String[] routeParts = route.getPathParts();
        String[] pathParts = Route.getPathParts(path);
        if (routeParts != pathParts) {
            return res;
        }
        for (int i = 0; i < routeParts.length; i++) {
            if (routeParts[i].startsWith("{")) {
                String key = routeParts[i].substring(1, routeParts[i].length()-1);
                res.put(key, pathParts[i]);
            }
        }
        return res;
    }
}
