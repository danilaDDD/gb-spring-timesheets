package ru.gb.danila.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.danila.timesheet.model.Role;
import ru.gb.danila.timesheet.model.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
  List<UserRole> findAllByUserId(Long id);
}