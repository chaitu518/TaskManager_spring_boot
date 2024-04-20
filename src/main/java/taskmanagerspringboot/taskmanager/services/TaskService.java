package taskmanagerspringboot.taskmanager.services;

import org.springframework.stereotype.Service;
import taskmanagerspringboot.taskmanager.entities.TaskEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    List<TaskEntity> tasks = new ArrayList<>();
    SimpleDateFormat deadlineformater = new SimpleDateFormat("YYYY-MM-DD");
    private int id=1;

    public TaskEntity addTasks(String title,String description,String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineformater.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        id++;
        return task;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }
    public TaskEntity getTaskById(int id){
        for(TaskEntity task : tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;
    }
    public TaskEntity updateTask(int id,String description, String deadline,Boolean completed) throws ParseException {
        TaskEntity task = getTaskById(id);
        if(task==null){
            return null;
        }
        if(description!=null){
            task.setDescription(description);
        }
        if(deadline!=null){
            task.setDeadline(deadlineformater.parse(deadline));
        }
        if(completed!=null){
            task.setCompleted(true);
        }
        return task;
    }

}
