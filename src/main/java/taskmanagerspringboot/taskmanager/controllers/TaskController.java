package taskmanagerspringboot.taskmanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanagerspringboot.taskmanager.dtos.CreateTaskDTO;
import taskmanagerspringboot.taskmanager.entities.TaskEntity;
import taskmanagerspringboot.taskmanager.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }
    @GetMapping("/")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        List<TaskEntity> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
        TaskEntity task = taskService.getTaskById(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body){
        TaskEntity task = taskService.addTasks(body.getTitle(),body.getDescription(),body.getDeadline());
        return ResponseEntity.ok(task);
    }
}
