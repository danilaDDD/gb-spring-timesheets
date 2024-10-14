package ru.gb.danila.timesheet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.danila.timesheet.model.Project;
import ru.gb.danila.timesheet.model.Timesheet;
import ru.gb.danila.timesheet.service.ProjectService;
import ru.gb.danila.timesheet.service.TimesheetService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {
   private final ProjectService projectService;
   private final TimesheetService timesheetService;

    public ProjectController(ProjectService projectService, TimesheetService timesheetService) {
        this.projectService = projectService;
        this.timesheetService = timesheetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id){
        Optional<Project> projectOptional = projectService.findById(id);

        return projectOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Project> findAll(){
        return projectService.findAll();
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findTimesheetsByProjectId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(timesheetService.findTimesheetsByProjectId(id));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project){
        return ResponseEntity.ok(projectService.create(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            projectService.delete(id);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
