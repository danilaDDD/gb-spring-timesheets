package ru.gb.danila.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.danila.timesheet.model.Project;
import ru.gb.danila.timesheet.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements CRUDService<Project>{
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project create(Project project) {
        return projectRepository.create(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.delete(id);
    }
}
