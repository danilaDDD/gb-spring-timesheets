package ru.gb.danila.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.danila.timesheet.model.Timesheet;
import ru.gb.danila.timesheet.repository.ProjectRepository;
import ru.gb.danila.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TimesheetService implements CRUDService<Timesheet> {
    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Timesheet> findAll() {
        return timesheetRepository.findAll();
    }

    @Override
    public Optional<Timesheet> findById(Long id) {
        return timesheetRepository.findById(id);
    }

    @Override
    public Timesheet create(Timesheet timesheet) {
        Long projectId = timesheet.getProjectId();
        if(projectId == null)
            throw new IllegalArgumentException("projectId is not null");
        projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException("project with this project id is not found"));

        return timesheetRepository.create(timesheet);
    }

    @Override
    public void delete(Long id) {
        timesheetRepository.delete(id);
    }

    public List<Timesheet> findCreatedAtAfter(LocalDate createdAtAfter) {
        return timesheetRepository.findCreatedAtAfter(createdAtAfter);
    }

    public List<Timesheet> findCreatedAtBefore(LocalDate createdAtBefore) {
        return timesheetRepository.findCreatedAtBefore(createdAtBefore);
    }

    public List<Timesheet> findTimesheetsByProjectId(Long projectId) {
        return timesheetRepository.findTimesheetsByProjectId(projectId);
    }
}
