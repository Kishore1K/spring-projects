package com.tasks.controller;

import com.tasks.model.TaskDTO;
import com.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping("/{id}/tasks")
    public ResponseEntity<TaskDTO> saveTask(
            @PathVariable (name = "id") Long userId, @RequestBody TaskDTO taskDTO
    ){

        return  new ResponseEntity<>(taskService.saveTask(userId, taskDTO), HttpStatus.CREATED);

    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable(name = "id") Long id){
        return  new ResponseEntity<>(taskService.getAllTasks(id), HttpStatus.OK);
    }

    @GetMapping("/{userId}/task/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable(name = "userId") Long userId, @PathVariable(name = "taskId") Long taskId){
        return new ResponseEntity<>(taskService.getTask(userId, taskId), HttpStatus.OK);
    }





}
