package ru.gb.danila.timesheetpage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.gb.danila.timesheetpage.dto.ProjectPageDto;
import ru.gb.danila.timesheetpage.dto.ProjectResponse;
import ru.gb.danila.timesheetpage.exceptions.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectPageService {
    private final RestTemplate restTemplate;
    private String rootUrl = "http://TIMESHEET-REST/projects";

    public List<ProjectPageDto> getAll(){
        try {
            return Arrays.asList(restTemplate.getForObject(rootUrl, ProjectResponse[].class))
                    .stream()
                    .map(this::convert)
                    .toList();
        }catch (HttpClientErrorException.NotFound e){
            throw new EntityNotFoundException("projects list api get 404 status");
        }
    }

    private ProjectPageDto convert(ProjectResponse projectResponse) {
        String link = getProjectPageLink(projectResponse.getId());
        return new ProjectPageDto(projectResponse.getId().toString(), projectResponse.getName(), link);
    }

    public String getProjectPageLink(Long id) {
        return String.format("/home/projects/%s", id);
    }

    public ProjectPageDto findById(Long id){
        String url = rootUrl + "/{id}";
        try {
            return Optional.of(restTemplate.getForObject(url, ProjectResponse.class, id))
                    .map(this::convert)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("project by id %s not found", id)));
        }catch (HttpClientErrorException.NotFound e){
            throw new EntityNotFoundException(String.format("project by id %s not found", id);
        }

    }
}
