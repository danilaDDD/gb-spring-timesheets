package ru.gb.danila.timesheet.exceptions;

public class ApiBadRequestException extends RuntimeException{
    public ApiBadRequestException(String message) {
        super(message);
    }
}
