package ru.gb.danila.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.danila.timesheet.exceptions.EntityNotFoundException;
import ru.gb.danila.timesheet.exceptions.HttpStatusNotFoundException;
import ru.gb.danila.timesheet.model.Employee;
import ru.gb.danila.timesheet.model.Timesheet;
import ru.gb.danila.timesheet.service.EmployeeService;
import ru.gb.danila.timesheet.service.TimesheetService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TimesheetService timesheetService;

    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        return employeeService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new HttpStatusNotFoundException("employee by id not found"));
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable Long id){
        try{
            return ResponseEntity.ok(employee);
        }catch (EntityNotFoundException e){
            throw new HttpStatusNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/{id}/timesheets")
    public List<Timesheet> findTimesheetsByEmployeeId(@PathVariable Long id){
       return timesheetService.findTimesheetsByProjectId(id);
    }
}
