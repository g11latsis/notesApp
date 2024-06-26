package gr.aueb.NoteApp.repositories;

import gr.aueb.NoteApp.dto.NotesDto;
import gr.aueb.NoteApp.model.Notes;
import gr.aueb.NoteApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByUserUsername(String username);
    List<Notes> findByUser(User user);
}
