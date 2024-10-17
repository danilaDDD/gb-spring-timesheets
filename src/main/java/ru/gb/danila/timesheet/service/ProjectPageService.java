package ru.gb.danila.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.danila.timesheet.dto.ProjectPageDto;
import ru.gb.danila.timesheet.exceptions.EntityNotFoundException;
import ru.gb.danila.timesheet.model.Project;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectPageService {
    private final  ProjectService projectService;

    public List<ProjectPageDto> getAll(){
        return projectService.findAll().stream()
                .map(this::convert)
                .toList();
    }

    private ProjectPageDto convert(Project project) {
        String link = getProjectPageLink(project.getId());
        return new ProjectPageDto(project.getId().toString(), project.getName(), link);
    }

    public String getProjectPageLink(Long id) {
        return String.format("/home/projects/%s", id);
    }

    public ProjectPageDto findById(Long id){
        Project project = projectService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("project by id %s not found", id)));

        return convert(project);
    }
}
