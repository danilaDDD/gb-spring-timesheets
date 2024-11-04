package ru.gb.danila.timesheetpage.page;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.gb.danila.timesheetpage.dto.ProjectPageDto;
import ru.gb.danila.timesheetpage.dto.ProjectResponse;
import ru.gb.danila.timesheetpage.exceptions.EntityNotFoundException;
import ru.gb.danila.timesheetpage.exceptions.HttpStatusNotFoundException;
import ru.gb.danila.timesheetpage.service.ProjectPageService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {
    private final ProjectPageService projectPageService;

    @GetMapping
    public String getAllProjects(Model model){
        try {

            model.addAttribute("projects", projectPageService.getAll());
        }catch (RestClientException e){
            e.printStackTrace();
        }

        return "projects-page.html";
    }

    @GetMapping("/{id}")
    public String getProjectPage(@PathVariable Long id, Model model){
        try{
            ProjectPageDto projectPageDto = projectPageService.findById(id);
            model.addAttribute("project", projectPageDto);

            return "project-page.html";
        }catch (EntityNotFoundException e){
            throw new HttpStatusNotFoundException(e.getMessage());
        }
    }
}
