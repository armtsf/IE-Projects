package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public abstract class ServiceHandler implements HttpHandler {
    public abstract HttpResponse execute(HttpExchange t);

    public void handle(HttpExchange t) throws IOException {
        HttpResponse response = execute(t);
        byte[] responseBody = response.getBody().getBytes();

        t.sendResponseHeaders(response.getStatus(), responseBody.length);
        Headers headers = t.getRequestHeaders();
        headers.add("Date", Calendar.getInstance().getTime().toString());
        headers.add("Content-Type", "text/html");
        for (String key: response.getHeaders().keySet()) {
            headers.add(key, response.getHeaders().get(key));
        }
        OutputStream os = t.getResponseBody();
        os.write(responseBody);
        os.close();
    }
}
