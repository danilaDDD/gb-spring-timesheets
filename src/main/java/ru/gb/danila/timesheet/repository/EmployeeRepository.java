package ru.gb.danila.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.danila.timesheet.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
