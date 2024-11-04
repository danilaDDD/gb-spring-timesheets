package ru.gb.danila.timesheetpage.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TimesheetResponse {
    private Long id;
    private Long projectId;
    private int minutes;
    private LocalDate createdAt;
    private Long employeeId;
}
