package gr.aueb.NoteApp.controllers;

import gr.aueb.NoteApp.dto.NotesDto;
import gr.aueb.NoteApp.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NotesRestController {

    private final INoteService noteService;

    @Autowired
    public NotesRestController(INoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<NotesDto> createNote(@RequestBody NotesDto notesDto, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(noteService.createNote(notesDto, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{notesId}")
    public ResponseEntity<NotesDto> updateNote(@RequestBody NotesDto notesDto, @PathVariable Long notesId) {
        try {
            return ResponseEntity.ok(noteService.updateNote(notesDto, notesId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{notesId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long notesId) {
        try {
            noteService.deleteNote(notesId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<NotesDto>> getNotesByUser(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(noteService.getNotesByUser(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/{notesId}")
//    public ResponseEntity<NotesDto> getNote(@PathVariable Long notesId) {
//        try {
//            return ResponseEntity.ok(noteService.getNote(notesId));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<List<NotesDto>> getAllNotes() {
        try {
            return ResponseEntity.ok(noteService.getAllNotes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
