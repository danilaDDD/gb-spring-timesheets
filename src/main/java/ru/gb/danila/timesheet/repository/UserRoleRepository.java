package ru.gb.danila.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.danila.timesheet.model.Role;
import ru.gb.danila.timesheet.model.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
  List<UserRole> findAllByUserId(Long id);
}