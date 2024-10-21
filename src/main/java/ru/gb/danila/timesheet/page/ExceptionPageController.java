package ru.gb.danila.timesheet.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.danila.timesheet.exceptions.HttpStatusNotFoundException;

@Controller
@ControllerAdvice
public class ExceptionPageController {
    @GetMapping("/not-found")
    public String notFoundPage(){
        return "not-found.html";
    }

    @GetMapping("/opps")
    public String getInternalServerErrorPage(){
        return "opps.html";
    }

    @ExceptionHandler(Exception.class)
    public String internalServerErrorHandler(){
        return "redirect:/opps";
    }


    @ExceptionHandler(HttpStatusNotFoundException.class)
    public String notFoundHandler(){
        return "redirect:/not-found";
    }
}
