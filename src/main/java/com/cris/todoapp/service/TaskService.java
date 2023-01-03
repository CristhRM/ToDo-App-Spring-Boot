package com.cris.todoapp.service;

import com.cris.todoapp.exceptions.ToDoExceptions;
import com.cris.todoapp.mapper.TaskInDTOToTask;
import com.cris.todoapp.persistence.entity.Task;
import com.cris.todoapp.persistence.entity.TaskStatus;
import com.cris.todoapp.persistence.repository.TaskRepository;
import com.cris.todoapp.service.dto.TaskInDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task create(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> findAll() {
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus) {
        return this.repository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void markTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not fount", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }


    public void delete(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not fount", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
