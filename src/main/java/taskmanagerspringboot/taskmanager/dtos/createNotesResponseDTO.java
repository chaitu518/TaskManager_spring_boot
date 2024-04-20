package taskmanagerspringboot.taskmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taskmanagerspringboot.taskmanager.entities.NotesEntity;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class createNotesResponseDTO {
    private int id;
    private NotesEntity notes;
}
