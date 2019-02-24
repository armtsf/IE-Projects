package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class ServiceHandler implements HttpHandler {
    private HashMap<String, String> uriVars;

    public abstract HttpResponse handleRequest(HttpExchange t);

    public void handle(HttpExchange t) throws IOException {
        HttpResponse response = handleRequest(t);
        byte[] responseBody = response.getBody().getBytes();

        Headers headers = t.getResponseHeaders();
        headers.set("Content-Type", "text/html");
        for (Map.Entry<String, String> entry: response.getHeaders().entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }
        t.sendResponseHeaders(response.getStatus(), responseBody.length);
        OutputStream os = t.getResponseBody();
        os.write(responseBody);
        os.close();
    }

    public HashMap<String, String> getUriVars() {
        return uriVars;
    }

    public void setUriVars(HashMap<String, String> uriVars) {
        this.uriVars = uriVars;
    }
}
