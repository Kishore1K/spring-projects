package com.tasks.controller;

import com.tasks.model.TaskDTO;
import com.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping("/{id}/tasks")
    public ResponseEntity<TaskDTO> saveTask(
            @RequestParam("id") Long userId, @RequestBody TaskDTO taskDTO
    ){

        return  new ResponseEntity<>(taskService.saveTask(userId, taskDTO), HttpStatus.CREATED);

    }




}
