package ru.gb.danila.timesheet.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.danila.timesheet.exceptions.HttpStatusNotFoundException;

@Controller
@ControllerAdvice
public class ExceptionHandlerController {
    @GetMapping
    public String notFoundPage(){
        return "not-found.html";
    }

    @ExceptionHandler(HttpStatusNotFoundException.class)
    public String notFoundHandler(){
        return "redirect:/not-found";
    }
}
