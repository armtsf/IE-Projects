package handlers;

import java.util.HashMap;

public class HttpResponse {
    private int status;
    private HashMap<String, String> headers = new HashMap<>();
    private String body;

    public HttpResponse(int status) {
        this.status = status;
    }

    public HttpResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
