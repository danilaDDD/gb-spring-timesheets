package ru.gb.danila.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    Long id;
    String name;
}
