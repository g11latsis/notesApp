package gr.aueb.NoteApp.service;

import gr.aueb.NoteApp.dto.NotesDto;
import gr.aueb.NoteApp.service.exceptions.EntityNotFoundException;

import java.util.List;

public class NoteServiceImpl implements INoteService{
    @Override
    public NotesDto createNote(NotesDto notesDto, String userId) throws Exception {
        return null;
    }

    @Override
    public NotesDto updateNote(NotesDto notesDto, Long notesId) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void deleteNote(Long notesId) throws EntityNotFoundException {

    }

    @Override
    public NotesDto getNote(Long notesId) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<NotesDto> getAllNotes() throws EntityNotFoundException {
        return List.of();
    }

    @Override
    public List<NotesDto> getNoteByUser(String userId) throws EntityNotFoundException {
        return List.of();
    }
}
