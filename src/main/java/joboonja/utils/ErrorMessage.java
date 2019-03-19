package joboonja.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ErrorMessage {
    @JsonProperty
    private Date date;

    @JsonProperty
    private String message;

    public ErrorMessage(Date date, String message) {
        this.date = date;
        this.message = message;
    }
}
