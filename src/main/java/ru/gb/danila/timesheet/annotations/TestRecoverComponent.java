package ru.gb.danila.timesheet.annotations;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestRecoverComponent {

    @Recover
    @SneakyThrows
    public void returnVoidWithException(String arg) {
        throw new Exception("void metthod");
    }

    @Recover
    @SneakyThrows
    public int returnIntWithIOException(){
        throw new IOException("not return int");
    }

    @Recover
    @SneakyThrows
    public String returnStringWithException(int a){
        throw new IOException("not return String");
    }
}
