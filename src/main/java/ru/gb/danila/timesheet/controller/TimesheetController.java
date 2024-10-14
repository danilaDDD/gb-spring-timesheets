package ru.gb.danila.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.danila.timesheet.model.Timesheet;
import ru.gb.danila.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {
    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @GetMapping
    public List<Timesheet> findAll(
            @RequestParam(name = "created-at-after", required = false) LocalDate createdAtAfter,
            @RequestParam(name = "created-at-before", required = false) LocalDate createdAtBefore
            ){
        if(createdAtAfter != null){
            return timesheetService.findCreatedAtAfter(createdAtAfter);
        } else if (createdAtBefore != null) {
            return timesheetService.findCreatedAtBefore(createdAtBefore);
        }
        return timesheetService.findAll();
    }

   @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id){
       Optional<Timesheet> timesheetOptional = timesheetService.findById(id);

       return timesheetOptional.map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());

   }

   @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet){
        try {
            Timesheet savedTimesheet = timesheetService.create(timesheet);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTimesheet);
        } catch (NoSuchElementException | IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
   }

    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> update(@PathVariable Long id, @RequestBody Timesheet timesheet){
        try {
            return ResponseEntity.ok(timesheetService.update(id, timesheet));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            timesheetService.delete(id);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
   }
}
