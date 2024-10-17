package ru.gb.danila.timesheet.exceptions;

public class HttpStatusNotFoundException extends RuntimeException{
    public HttpStatusNotFoundException(String message) {
        super(message);
    }
}
