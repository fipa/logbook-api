package logbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logbook.domain.Note;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    private List<Note> allNotes;

    void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return this.allNotes;
    }
    
    
    public Note findOne(Long id) throws Exception {
        return noteRepository.findById(id).orElseThrow(()->new Exception());
    }

    public Note saveNote(Note note) {
        note.setTimestamp(LocalDateTime.now());
        noteRepository.save(note);
        return note;
    }


}