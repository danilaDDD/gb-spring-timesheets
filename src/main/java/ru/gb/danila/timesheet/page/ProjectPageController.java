package ru.gb.danila.timesheet.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.danila.timesheet.dto.ProjectPageDto;
import ru.gb.danila.timesheet.exceptions.EntityNotFoundException;
import ru.gb.danila.timesheet.exceptions.HttpStatusNotFoundException;
import ru.gb.danila.timesheet.service.ProjectPageService;

import java.util.List;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {
    private final ProjectPageService pageService;

    @GetMapping
    public String getAllProjects(Model model){
        List<ProjectPageDto> projectPageDtos = pageService.getAll();
        model.addAttribute("projects", projectPageDtos);

        return "projects-page.html";
    }

    @GetMapping("/{id}")
    public String getProjectPage(@PathVariable Long id, Model model){
        try{
            ProjectPageDto projectPageDto = pageService.findById(id);
            model.addAttribute("project", projectPageDto);

            return "project-page.html";
        }catch (EntityNotFoundException e){
            throw new HttpStatusNotFoundException(e.getMessage());
        }
    }
}
