package base;

import com.sun.net.httpserver.HttpHandler;

public class Route {
    private String path;
    private HttpHandler handler;
    private String[] pathParts;

    public Route(String path, HttpHandler handler) {
        this.handler = handler;
        this.path = path;
        this.pathParts = getPathParts(path);
    }

    public HttpHandler getHandler() {
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
}
