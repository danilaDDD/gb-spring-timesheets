package ru.gb.danila.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.danila.timesheet.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
