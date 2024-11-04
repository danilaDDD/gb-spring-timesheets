package ru.gb.danila.timesheetpage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {
//    private final TimesheetService timesheetService;
//    private final ProjectService projectService;
//
//    public TimesheetPageDto findById(Long id){
//        Timesheet timesheet = timesheetService.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException(String.format("timesheet not found by id=%s", id)));
//
//        Project project = projectService.findById(timesheet.getProjectId())
//                .orElseThrow(() -> new EntityNotFoundException(String.format("project not found by id=%s", timesheet.getProjectId())));
//
//        return convert(timesheet, project);
//    }
//
//    private TimesheetPageDto convert(Timesheet timesheet, Project project) {
//        TimesheetPageDto dto = new TimesheetPageDto();
//        dto.setTimesheetId(timesheet.getId().toString());
//        dto.setProjectName(project.getName());
//        dto.setTimesheetCreatedAt(timesheet.getCreatedAt().toString());
//        dto.setTimesheetMinutes(String.valueOf(timesheet.getMinutes()));
//        dto.setProjectId(project.getId().toString());
//
//        return dto;
//    }
//
//    public List<TimesheetPageDto> findAll() {
//        Map<Long, Project> projectToId = new HashMap<>();
//        List<Project> projects = projectService.findAll();
//        for(Project project: projects){
//            projectToId.put(project.getId(), project);
//        }
//
//        List<Timesheet> timesheets = timesheetService.findAll();
//        List<TimesheetPageDto> result = new ArrayList<>(timesheets.size());
//
//        for(Timesheet timesheet: timesheets){
//            Project project = projectToId.get(timesheet.getId());
//            if(project == null){
//                throw new EntityNotFoundException(String.format("project for timesheet %s not found", project));
//            }
//            result.add(convert(timesheet, project));
//        }
//
//        return result;
//    }
}
