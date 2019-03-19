package joboonja.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ResponseMessage {
    @JsonProperty
    private Date date;

    @JsonProperty
    private String message;

    public ResponseMessage(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
