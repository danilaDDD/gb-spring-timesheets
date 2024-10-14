package ru.gb.danila.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Timesheet {
    private Long id;
    private Long projectId;
    private int minutes;
    private LocalDate createdAt;
}
