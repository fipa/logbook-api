package logbook.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import logbook.domain.Note;
import logbook.service.NoteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {

	@Autowired
    private NoteService noteService;
    
    // displays all notes
    @GetMapping("/notes")
    @ResponseBody
    public List<Note> allNotes() {
    	return noteService.findAll();
    }
    
    @PostMapping("/notes")
    Note newNote(@RequestBody Note newNote) {
      return noteService.saveNote(newNote);
    }

    @GetMapping("/notes/{id}")
    Note oneNote(@PathVariable Long id) throws Exception{
      return noteService.findOne(id);
    }
    
    @PutMapping("/notes/{id}")
    Note replaceNote(@RequestBody Note newNote, @PathVariable Long id) {
    	newNote.setTimestamp(LocalDateTime.now()); // podria ser un buen ejercicio, eliminar esta linea y que la prueba falle
    	return noteService.replaceNote(newNote,id);
    }

    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable Long id) {
      noteService.deleteNote(id);
    }
}