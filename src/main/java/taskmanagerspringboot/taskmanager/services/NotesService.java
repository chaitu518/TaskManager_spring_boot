package taskmanagerspringboot.taskmanager.services;

import org.springframework.stereotype.Service;
import taskmanagerspringboot.taskmanager.entities.NotesEntity;
import taskmanagerspringboot.taskmanager.entities.TaskEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotesService {
    private TaskService taskService;
    public NotesService(TaskService taskService){
        this.taskService=taskService;
    }
    HashMap<Integer,NotesHandler> taskNoteHandler = new HashMap<>();
    class NotesHandler{
        protected int id=1;
        protected ArrayList<NotesEntity> notes=new ArrayList<>();
    }
    public List<NotesEntity> getNotes(int taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task==null) return null;
        if(!taskNoteHandler.containsKey(taskId)){
            taskNoteHandler.put(taskId,taskNoteHandler.getOrDefault(taskId,new NotesHandler()));

        }
        return taskNoteHandler.get(taskId).notes;
    }
    public NotesEntity addNotes(int taskId,String title,String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task==null) return null;
        if(!taskNoteHandler.containsKey(taskId)){
            taskNoteHandler.put(taskId,taskNoteHandler.getOrDefault(taskId,new NotesHandler()));
        }
        NotesHandler taskNotesHandler = taskNoteHandler.get(taskId);
        NotesEntity note = new NotesEntity();
        note.setId(taskNotesHandler.id);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHandler.notes.add(note);
        taskNotesHandler.id++;
        return note;
    }



}
