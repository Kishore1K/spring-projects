package com.tasks.service;

import com.tasks.entity.Task;
import com.tasks.entity.User;
import com.tasks.model.TaskDTO;
import com.tasks.repository.TasksRepository;
import com.tasks.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TasksRepository tasksRepository;
    @Override
    public TaskDTO saveTask(Long userId, TaskDTO taskDTO) {
        User user=userRepository.findById(userId).get();
        Task  task = modelMapper.map(taskDTO, Task.class);
        Task savedTask  = tasksRepository.save(task);
        return modelMapper.map(savedTask, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getAllTasks(Long userId) {
        return null;
    }
}
