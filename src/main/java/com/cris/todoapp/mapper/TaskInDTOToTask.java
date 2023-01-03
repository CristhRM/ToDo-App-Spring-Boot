package com.cris.todoapp.mapper;

import com.cris.todoapp.persistence.entity.Task;
import com.cris.todoapp.persistence.entity.TaskStatus;
import com.cris.todoapp.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task> {
    @Override
    public Task map(TaskInDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setFinished(false);
        return task;
    }
}
