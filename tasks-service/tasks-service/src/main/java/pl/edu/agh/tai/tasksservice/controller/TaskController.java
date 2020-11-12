package pl.edu.agh.tai.tasksservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.tasksservice.domain.TaskDto;
import pl.edu.agh.tai.tasksservice.mapper.TaskMapper;
import pl.edu.agh.tai.tasksservice.service.DbService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*") 
public class TaskController {


    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;



    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<TaskDto> getTasks()
    {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException
    {
        return taskMapper.mapToTaskDto(service.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));
    }

  //  @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
//    public void deleteTask(@PathVariable Long taskId){
  //      service.deleteTask(taskId);
  //  }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}
