package taskmanagerspringboot.taskmanager.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import taskmanagerspringboot.taskmanager.dtos.CreateTaskDTO;
import taskmanagerspringboot.taskmanager.dtos.ErrorResponseDTO;
import taskmanagerspringboot.taskmanager.dtos.TaskResponseDTO;
import taskmanagerspringboot.taskmanager.dtos.UpdateTaskDTO;
import taskmanagerspringboot.taskmanager.entities.TaskEntity;
import taskmanagerspringboot.taskmanager.services.NotesService;
import taskmanagerspringboot.taskmanager.services.TaskService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    private NotesService notesService;
    public TaskController(TaskService taskService,NotesService notesService){
        this.taskService=taskService;
        this.notesService=notesService;
    }
    @GetMapping("/")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        List<TaskEntity> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        TaskEntity task = taskService.getTaskById(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        ModelMapper modelMapper = new ModelMapper();
        TaskResponseDTO taskResponseDTO = modelMapper.map(task, TaskResponseDTO.class);
        taskResponseDTO.setNotes(notesService.getNotes(id));
        return ResponseEntity.ok(taskResponseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        TaskEntity task = taskService.addTasks(body.getTitle(),body.getDescription(),body.getDeadline());
        return ResponseEntity.ok(task);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        TaskEntity task = taskService.updateTask(id,body.getDescription(),body.getDeadline(),body.getCompleted());
        return ResponseEntity.ok(task);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }
        return ResponseEntity.badRequest().body(new ErrorResponseDTO("Internal server error"));

    }

}
