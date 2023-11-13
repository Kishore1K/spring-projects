package com.tasks.service;

import com.tasks.Exception.NotAuthourized;
import com.tasks.Exception.TaskNotFound;
import com.tasks.Exception.UserNotFoundException;
import com.tasks.entity.Task;
import com.tasks.entity.Users;
import com.tasks.model.TaskDTO;
import com.tasks.repository.TasksRepository;
import com.tasks.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        Users user=userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException(String.format("User Id %d is Not Found", userId))
        );
        Task  task = modelMapper.map(taskDTO, Task.class);
        task.setUser(user);
        Task savedTask  = tasksRepository.save(task);

        return modelMapper.map(savedTask, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getAllTasks(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException(String.format("User Id %d is Not Found", userId))
        );
        List<Task> taskList = tasksRepository.findAllByUserId(userId);
        return taskList.stream().map(
                task -> modelMapper.map(task, TaskDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(Long userId, Long taskId){
        Users user = userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException(String.format("User Id %d is Not Found", userId))
        );
        Task task = tasksRepository.findById(taskId).orElseThrow(
                ()->new TaskNotFound(String.format("Task Id %d is Not Found", taskId))
        );
        if(!Objects.equals(user.getId(), task.getUser().getId())){
            throw new NotAuthourized("Your Not Authorized to use");
        }
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {
        Users user = userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException(String.format("User Id %d is Not Found", userId))
        );
        Task task = tasksRepository.findById(taskId).orElseThrow(
                ()->new TaskNotFound(String.format("Task Id %d is Not Found", taskId))
        );
        if(!Objects.equals(task.getUser().getId(), user.getId())){
            throw new NotAuthourized("Your Not Authorized to Delete");
        }
        tasksRepository.delete(task);
    }
}
