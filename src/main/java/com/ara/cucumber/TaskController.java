package com.ara.cucumber;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {this.taskRepository = taskRepository;}

    @GetMapping
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    public TaskEntity createTask(@RequestBody TaskEntity task) {
        return taskRepository.save(task);
    }

}
