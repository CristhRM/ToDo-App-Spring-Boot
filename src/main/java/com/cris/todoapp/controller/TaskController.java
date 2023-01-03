package com.cris.todoapp.controller;

import com.cris.todoapp.persistence.entity.Task;
import com.cris.todoapp.persistence.entity.TaskStatus;
import com.cris.todoapp.service.TaskService;
import com.cris.todoapp.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task create(@RequestBody TaskInDTO taskInDTO) {
        return this.service.create(taskInDTO);
    }

    @GetMapping
    public List<Task> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByTaskStatus(@PathVariable("status") TaskStatus taskStatus) {
        return this.service.findAllByTaskStatus(taskStatus);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> markTaskAsFinished(@PathVariable("id") Long id) {
        this.service.markTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
