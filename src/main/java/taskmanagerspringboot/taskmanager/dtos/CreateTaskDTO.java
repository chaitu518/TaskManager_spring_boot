package taskmanagerspringboot.taskmanager.dtos;

import lombok.Data;

@Data
public class CreateTaskDTO {
    private String title;
    private String description;
    private String deadline;
}
