package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public abstract class ServiceHandler implements HttpHandler {
    public abstract HttpResponse execute(HttpExchange t);

    public void handle(HttpExchange t) throws IOException {
        HttpResponse response = execute(t);
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
}
