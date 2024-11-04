package ru.gb.danila.timesheetpage.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.danila.timesheetpage.dto.TimesheetPageDto;
import ru.gb.danila.timesheetpage.exceptions.EntityNotFoundException;
import ru.gb.danila.timesheetpage.exceptions.HttpStatusNotFoundException;
import ru.gb.danila.timesheetpage.service.TimesheetPageService;

import java.util.List;

@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {
    private final TimesheetPageService pageService;

    @GetMapping
    public String getAllTimesheetsPage(Model model){
        try {
            List<TimesheetPageDto> timesheets = pageService.findAll();
            model.addAttribute("timesheets", timesheets);

            return "timesheets-page.html";
        }catch (EntityNotFoundException e){
            throw new HttpStatusNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model){
        try {
            TimesheetPageDto timesheetPageDto = pageService.findById(id);
            model.addAttribute("timesheet", timesheetPageDto);
            return "timesheet-page.html";

        } catch (EntityNotFoundException e){
            throw new HttpStatusNotFoundException(e.getMessage());
        }
    }
}
