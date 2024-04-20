package taskmanagerspringboot.taskmanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanagerspringboot.taskmanager.dtos.CreateNotesDTO;
import taskmanagerspringboot.taskmanager.dtos.createNotesResponseDTO;
import taskmanagerspringboot.taskmanager.entities.NotesEntity;
import taskmanagerspringboot.taskmanager.services.NotesService;
import taskmanagerspringboot.taskmanager.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks/{id}/notes")
public class NotesController {
    private TaskService taskService;
    private NotesService notesService;
    public NotesController(TaskService taskService,NotesService notesService){
        this.taskService=taskService;
        this.notesService=notesService;
    }
    @GetMapping("/")
    public ResponseEntity<List<NotesEntity>> getNotes(@PathVariable("id") Integer taskId){
        return ResponseEntity.ok(notesService.getNotes(taskId));
    }
    @PostMapping("/")
    public ResponseEntity<createNotesResponseDTO> addNotes(@PathVariable("id") Integer id, @RequestBody CreateNotesDTO body){
       NotesEntity notesEntity = notesService.addNotes(id,body.getTitle(), body.getBody());

       return ResponseEntity.ok(new createNotesResponseDTO(id,notesEntity));
    }

}
