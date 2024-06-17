package gr.aueb.NoteApp.service;

import gr.aueb.NoteApp.dto.NotesDto;
import gr.aueb.NoteApp.dto.UserDto;
import gr.aueb.NoteApp.model.Notes;
import gr.aueb.NoteApp.model.User;
import gr.aueb.NoteApp.repositories.UserRepository;
import gr.aueb.NoteApp.service.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.aueb.NoteApp.repositories.NotesRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoteServiceImpl implements INoteService {

    private final NotesRepository notesRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoteServiceImpl(NotesRepository notesRepository, UserRepository userRepository) {
        this.notesRepository = notesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NotesDto createNote(NotesDto notesDto, String userId) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(Long.parseLong(userId))
                    .orElseThrow(() -> new EntityNotFoundException(User.class, Long.parseLong(userId)));

            notesDto.setDate(new Date());

            Notes notes = this.DtoToNotes(notesDto);
            notes.setUser(user);

            Notes result = this.notesRepository.save(notes);

            log.info("Note created: " + result);
            return this.NotesToDto(result);

        } catch (Exception e) {
            log.error("Error while creating note", e);
            throw new EntityNotFoundException(User.class, Long.parseLong(userId));
        }
    }

    @Override
    public NotesDto updateNote(NotesDto notesDto, Long notesId) throws EntityNotFoundException {
        try {
            Notes notes = notesRepository.findById(notesId)
                    .orElseThrow(() -> new EntityNotFoundException(Notes.class, notesId));

            notes.setTitle(notesDto.getTitle());
            notes.setDescription(notesDto.getDescription());
            notes.setDate(notesDto.getDate());

            Notes result = this.notesRepository.save(notes);
            log.info("Note updated: " + result);
            return NotesToDto(result);

        } catch (Exception e) {
            log.error("Error while updating note", e);
            throw new EntityNotFoundException(Notes.class, notesId);
        }
    }

    @Override
    public void deleteNote(Long notesId) throws EntityNotFoundException {
        try {
            notesRepository.deleteById(notesId);
        } catch (Exception e) {
            log.error("Error while deleting note", e);
            throw new EntityNotFoundException(Notes.class, notesId);
        }
    }

    @Override
    public List<NotesDto> getNotesByUser(String userId) throws EntityNotFoundException {
        try {
            User user = userRepository.findById(Long.parseLong(userId))
                    .orElseThrow(() -> new EntityNotFoundException(User.class, Long.parseLong(userId)));
            List<Notes> notes = notesRepository.findByUser(user);
            List<NotesDto> allNotes= notes.stream().map((note)->this.NotesToDto(note)).collect(Collectors.toList());
            log.info("Notes retrieved: " + notes);
            return allNotes;
        } catch (Exception e) {
            log.error("Error while getting notes", e);
            throw new EntityNotFoundException(User.class, Long.parseLong(userId));
        }
    }

    @Override
    public NotesDto getNote(Long notesId) throws EntityNotFoundException {
        try {
            Notes notes = notesRepository.findById(notesId)
                    .orElseThrow(() -> new EntityNotFoundException(Notes.class, notesId));
            log.info("Note retrieved: " + notes);
            return this.NotesToDto(notes);
        } catch (Exception e) {
            log.error("Error while getting note", e);
            throw new EntityNotFoundException(Notes.class, notesId);
        }
    }

    @Override
    public List<NotesDto> getAllNotes() throws EntityNotFoundException {
        try {
            List<Notes> notes = notesRepository.findAll();
            List<NotesDto> allNotes= notes.stream().map((note)->this.NotesToDto(note)).collect(Collectors.toList());
            log.info("Notes retrieved: " + notes);
            return allNotes;
        } catch (Exception e) {
            log.error("Error while getting notes", e);
            throw new EntityNotFoundException(Notes.class, 0L);
        }
    }


    public Notes DtoToNotes(NotesDto notesDto) {
        Notes notes = new Notes();
        notes.setId(notesDto.getId());
        notes.setTitle(notesDto.getTitle());
        notes.setDescription(notesDto.getDescription());
        notes.setDate(notesDto.getDate());
        return notes;
    }

    public NotesDto NotesToDto(Notes notes) {
        NotesDto notesDto = new NotesDto();
        notesDto.setId(notes.getId());
        notesDto.setTitle(notes.getTitle());
        notesDto.setDescription(notes.getDescription());
        notesDto.setDate(notes.getDate());
        return notesDto;
    }

    public User DtoToUSer(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto UserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
