package edu.project.app.dao.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.project.app.entity.Task;

public interface TaskRepository {
    Task save(Task entity);

    Optional<Task> findById(UUID id);

    List<Task> findAll();

    boolean existsById(UUID id);

    Task updateById(Task entity);

    boolean deleteById(UUID id);

    Optional<Task> findWithOutDescription(UUID id);

    List<Task> findAllWithOutDescription();

    List<Task> findAllWithOutDescription(int limit);

    Optional<String> findDescriptionById(UUID id);
}
