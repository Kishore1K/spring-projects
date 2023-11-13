package com.tasks.service;

import com.tasks.Exception.NotAuthourized;
import com.tasks.model.TaskDTO;

import java.util.List;

public interface TaskService {

    public TaskDTO saveTask(Long userId, TaskDTO taskDTO);

    public List<TaskDTO> getAllTasks(Long userId);

    public TaskDTO getTask(Long userId, Long taskId) throws NotAuthourized;

}
