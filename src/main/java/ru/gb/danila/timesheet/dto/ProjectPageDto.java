package ru.gb.danila.timesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectPageDto {
    private String id;
    private String name;
    private String link;
}
