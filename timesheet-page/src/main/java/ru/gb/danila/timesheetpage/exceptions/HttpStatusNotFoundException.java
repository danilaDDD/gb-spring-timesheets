package ru.gb.danila.timesheetpage.exceptions;

public class HttpStatusNotFoundException extends RuntimeException{
    public HttpStatusNotFoundException(String message) {
        super(message);
    }
}
