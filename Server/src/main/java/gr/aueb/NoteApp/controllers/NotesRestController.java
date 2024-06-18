package gr.aueb.NoteApp.controllers;

import gr.aueb.NoteApp.dto.NotesDto;
import gr.aueb.NoteApp.service.INoteService;
import gr.aueb.NoteApp.service.exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<NotesDto> createNote(@RequestBody NotesDto notesDto, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(noteService.createNote(notesDto, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update a note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note updated"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/{notesId}")
    public ResponseEntity<NotesDto> updateNote(@RequestBody NotesDto notesDto, @PathVariable Long notesId) {
        try {
            return ResponseEntity.ok(noteService.updateNote(notesDto, notesId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Delete a note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/{notesId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long notesId) {
        try {
            noteService.deleteNote(notesId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get all notes by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes found"),
            @ApiResponse(responseCode = "404", description = "Notes not found")
    })
    @GetMapping("/user/{username}")
    public ResponseEntity<List<NotesDto>> getAllNotesByUsername(@PathVariable String username) {
        try {
            List<NotesDto> notes = noteService.getNotesByUserUsername(username);
            return ResponseEntity.ok(notes);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Get a note by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note found"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @GetMapping("/{notesId}")
    public ResponseEntity<NotesDto> getNote(@PathVariable Long notesId) {
        try {
            return ResponseEntity.ok(noteService.getNote(notesId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get all notes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes found"),
            @ApiResponse(responseCode = "404", description = "Notes not found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<NotesDto>> getAllNotes() {
        try {
            return ResponseEntity.ok(noteService.getAllNotes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
