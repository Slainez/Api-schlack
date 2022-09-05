package fr.m2i.apischlack.response;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String time;
    private String error;
    private int status;
    private String path;

    public ErrorResponse(String error, int status, String path) {
        this.time = LocalDateTime.now().toString();
        this.error = error;
        this.status = status;
        this.path = path;
    }

    public String getCalendar() {
        return time;
    }

    public void setCalendar(String calendar) {
        this.time = calendar;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}