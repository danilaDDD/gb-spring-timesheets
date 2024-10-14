package ru.gb.danila.timesheet.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<Entity> {
    Optional<Entity> findById(Long id);

    List<Entity> findAll();

    Entity create(Entity entity);

    void delete(Long id);
}
