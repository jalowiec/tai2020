package pl.edu.agh.tai.tasksservice.mapper;


import org.springframework.stereotype.Component;
import pl.edu.agh.tai.tasksservice.domain.Task;
import pl.edu.agh.tai.tasksservice.domain.TaskDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Task mapToTask(final TaskDto taskDto){
        return new Task(
            taskDto.getId(),
            taskDto.getTitle(),
            taskDto.getContent()
        );
    }

    public TaskDto mapToTaskDto(final Task task){
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getContent()
        );
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList){
        return taskList.stream()
                .map(n->new TaskDto(n.getId(), n.getTitle(), n.getContent()))
                .collect(Collectors.toList());
    }

}
