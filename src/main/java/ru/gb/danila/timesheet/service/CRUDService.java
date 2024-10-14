package ru.gb.danila.timesheet.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<Entity> {
    List<Entity> findAll();

    Optional<Entity> findById(Long id);

    Entity create(Entity entity);

    void delete(Long id);
}
