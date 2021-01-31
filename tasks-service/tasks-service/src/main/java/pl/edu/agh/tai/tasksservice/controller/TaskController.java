package pl.edu.agh.tai.tasksservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.tasksservice.domain.TaskDto;
import pl.edu.agh.tai.tasksservice.mapper.TaskMapper;
import pl.edu.agh.tai.tasksservice.service.DbService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users/{userId}")
public class TaskController {


    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getTasksByUserId(@PathVariable(value = "userId") int userId){

        logger.info("getTasks");
        //return taskMapper.mapToTaskDtoList(service.getAllTasks());
        return taskMapper.mapToTaskDtoList(service.getTasksByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException
    {
        logger.info("getTask");
        return taskMapper.mapToTaskDto(service.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        logger.info("deleteTask");
        service.deleteTask(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        logger.info("updateTask");
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        logger.info("createTask");
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}
