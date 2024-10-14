package ru.gb.danila.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.danila.timesheet.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class ProjectRepository implements CRUDRepository<Project>{
    private static long sequence = 1L;
    private static final List<Project> projects = new ArrayList<>();

    @Override
    public Optional<Project> findById(Long id) {
        return projects.stream()
                .filter(project -> project.getId().equals(id)).findFirst();
    }

    @Override
    public List<Project> findAll() {
        return List.copyOf(projects);
    }

    @Override
    public Project create(Project project) {
        project.setId(sequence++);
        projects.add(project);

        return project;
    }

    @Override
    public Project update(Long id, Project project) {
        Project existProject = findById(id).orElseThrow(NoSuchElementException::new);
        existProject.setName(project.getName());

        return existProject;
    }

    @Override
    public void delete(Long id) {
        Project project = findById(id).orElseThrow(NoSuchElementException::new);
        projects.remove(project);
    }
}
