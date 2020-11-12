package pl.edu.agh.tai.tasksservice.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.tasksservice.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    Optional<Task> findById(Long id);

    @Override
    long count();



}
