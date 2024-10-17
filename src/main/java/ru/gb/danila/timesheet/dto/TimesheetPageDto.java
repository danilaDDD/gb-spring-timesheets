package ru.gb.danila.timesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetPageDto {
    private String projectId;
    private String timesheetId;
    private String timesheetMinutes;
    private String timesheetCreatedAt;
    private String projectName;
}
