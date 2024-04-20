package taskmanagerspringboot.taskmanager.services;

import org.springframework.stereotype.Service;
import taskmanagerspringboot.taskmanager.entities.TaskEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    List<TaskEntity> tasks = new ArrayList<>();
    private int id=1;

    public TaskEntity addTasks(String title,String description,String deadline){
        TaskEntity task = new TaskEntity();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        //task.setDeadline();
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
}
