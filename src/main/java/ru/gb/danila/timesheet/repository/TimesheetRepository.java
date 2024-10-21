package ru.gb.danila.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.danila.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findAllByCreatedAtAfter(LocalDate createdAt);

    List<Timesheet> findAllByCreatedAtBefore(LocalDate createdAt);

    List<Timesheet> findAllByProjectId(Long projectId);

    List<Timesheet> findAllByEmployeeId(Long employeeId);
}
