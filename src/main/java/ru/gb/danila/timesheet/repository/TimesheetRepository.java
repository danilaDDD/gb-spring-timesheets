package ru.gb.danila.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.danila.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class TimesheetRepository implements CRUDRepository<Timesheet>{
    private static long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    public Optional<Timesheet> findById(Long id){
        return timesheets.stream()
                .filter(timesheet -> timesheet.getId().equals(id))
                .findFirst();
    }

    public List<Timesheet> findAll(){
        return List.copyOf(timesheets);
    }

    public Timesheet create(Timesheet timesheet){
        timesheet.setId(sequence++);
        timesheet.setCreatedAt(LocalDate.now());
        timesheets.add(timesheet);

        return timesheet;
    }

    public void delete(Long id){
        findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Timesheet> findCreatedAtAfter(LocalDate createdAtAfter) {
        return timesheets.stream()
                .filter(timesheet -> timesheet.getCreatedAt().isAfter(createdAtAfter))
                .toList();
    }

    public List<Timesheet> findCreatedAtBefore(LocalDate createdAtBefore) {
        return timesheets.stream()
                .filter(timesheet -> timesheet.getCreatedAt().isBefore(createdAtBefore))
                .toList();
    }

    public List<Timesheet> findTimesheetsByProjectId(Long projectId) {
        return timesheets.stream()
                .filter(it -> it.getProjectId().equals(projectId))
                .toList();
    }
}
