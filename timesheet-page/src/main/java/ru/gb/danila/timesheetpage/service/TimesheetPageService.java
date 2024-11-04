package ru.gb.danila.timesheetpage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.gb.danila.timesheetpage.dto.ProjectResponse;
import ru.gb.danila.timesheetpage.dto.TimesheetPageDto;
import ru.gb.danila.timesheetpage.dto.TimesheetResponse;
import ru.gb.danila.timesheetpage.exceptions.EntityNotFoundException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {
    private static final String rootUrl = "http://TIMESHEET-REST/timesheets";

    private final RestTemplate restTemplate;
    private final ProjectPageService projectPageService;

    public TimesheetPageDto findById(Long id){
        String url = rootUrl + "/{id}";
        TimesheetResponse timesheetResponse = null;
        ProjectResponse projectResponse;

        try {
            timesheetResponse = Optional.of(restTemplate.getForObject(url, TimesheetResponse.class, id))
                    .orElseThrow(() -> new EntityNotFoundException(id, TimesheetResponse.class));

            TimesheetResponse finalTimesheetResponse = timesheetResponse;
            projectResponse = projectPageService.getProjectResponseById(timesheetResponse.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException(finalTimesheetResponse.getProjectId(), ProjectResponse.class));
        }catch (HttpClientErrorException.NotFound e){
            throw new EntityNotFoundException(timesheetResponse.getId(),TimesheetResponse.class);
        }

        return convert(timesheetResponse, projectResponse);
    }
//
    private TimesheetPageDto convert(TimesheetResponse timesheet, ProjectResponse project) {
        TimesheetPageDto dto = new TimesheetPageDto();
        dto.setTimesheetId(timesheet.getId().toString());
        dto.setProjectName(project.getName());
        dto.setTimesheetCreatedAt(timesheet.getCreatedAt().toString());
        dto.setTimesheetMinutes(String.valueOf(timesheet.getMinutes()));
        dto.setProjectId(project.getId().toString());

        return dto;
    }

    public List<TimesheetPageDto> findAll() {
        try {

            List<TimesheetResponse> timesheetResponses = List.of(restTemplate.getForObject(rootUrl, TimesheetResponse[].class));
            List<TimesheetPageDto> timesheetPageDtoList = new ArrayList<>(timesheetResponses.size());

            Map<Long, ProjectResponse> projectResponseMap = new HashMap<>();
            for(var projectResponse: projectPageService.getProjectResponses()){
                projectResponseMap.put(projectResponse.getId(), projectResponse);
            }

            for(var timesheetResponse: timesheetResponses){
                ProjectResponse projectResponse = projectResponseMap.get(timesheetResponse.getProjectId());
                timesheetPageDtoList.add(convert(timesheetResponse, projectResponse));
            }

            return timesheetPageDtoList;
        }catch (HttpClientErrorException.NotFound e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
