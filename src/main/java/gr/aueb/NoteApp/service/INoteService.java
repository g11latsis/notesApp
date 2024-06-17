package gr.aueb.NoteApp.service;

import gr.aueb.NoteApp.dto.NotesDto;
import gr.aueb.NoteApp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface INoteService {
    NotesDto createNote(NotesDto notesDto,String userId) throws Exception;
    NotesDto updateNote(NotesDto notesDto,Long notesId) throws EntityNotFoundException;
    void deleteNote(Long notesId) throws EntityNotFoundException;
    NotesDto getNote(Long notesId) throws EntityNotFoundException;
    List<NotesDto> getAllNotes()  throws EntityNotFoundException;
    List<NotesDto> getNotesByUser(String userId) throws EntityNotFoundException;
}
