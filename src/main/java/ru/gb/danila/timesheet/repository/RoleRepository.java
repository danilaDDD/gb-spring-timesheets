package ru.gb.danila.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.danila.timesheet.model.Role;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByIdIn(List<Long> id);
}