package com.tasks.service;

import com.tasks.model.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Override
    public TaskDTO saveTask(Long userId, TaskDTO taskDTO) {
        return null;
    }

    @Override
    public List<TaskDTO> getAllTasks(Long userId) {
        return null;
    }
}
