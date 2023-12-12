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
    private HashMap<String, Integer> dictionary;

    @PostConstruct
    void syncAllNotes() {
        this.allNotes = this.findAll();
        this.updateDictionary();
    }

    void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        List<Note> noteList = (List<Note>) noteRepository.findAll();
        this.allNotes = noteList;
        return noteList;

    }

    public Note findOne(Long id) throws Exception {
        return noteRepository.findById(id).orElseThrow(()->new Exception());
    }

    public Note saveNote(Note note) {
        note.setTimestamp(LocalDateTime.now());
        noteRepository.save(note);
        this.syncAllNotes();
        return note;
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
        this.syncAllNotes();
    }

    List<Note> getAllNotes() {
        return allNotes;
    }

    /* -------------------- unimplemented methods --------------------- */

    // should filter notes by a given string
    public List<Note> findAllBy(String filter) {
        return this.allNotes;
    }

    // should clone a note in the DB
    public void cloneNote(Long id) {

        Note note = noteRepository.findById(id).get();

        Note clonedNote = new Note();
        clonedNote.setTitle(note.getTitle());
        clonedNote.setContent(note.getContent());

        noteRepository.save(clonedNote);

    }

    public Map<String, Integer> getWords(String filter, Integer repetitionFactor) {

    	return dictionary.entrySet().stream()
    			.filter(entry -> filter == null || filter.equals(entry.getKey()))
    			.filter(entry -> repetitionFactor == null || (entry.getValue() != null && entry.getValue() > repetitionFactor))
    			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void updateDictionary() {
        dictionary = new HashMap<>();
        String unwantedCharacters = "[,|.|:|?|!]";

        for(Note note : allNotes) {
            for (String word: note.getContent().toLowerCase().replaceAll(unwantedCharacters, "").split(" ")) {
                if (dictionary.containsKey(word)) {
                    dictionary.replace(word, dictionary.get(word) + 1);
                } else {
                    dictionary.put(word, 1);
                }
            }
        }
    }

	public Note replaceNote(Note newNote, Long id) {
	      return noteRepository.findById(id).map(note -> {
	    	          note.setTitle(newNote.getTitle());
	    	          note.setContent(newNote.getContent());
	    	          note.setTimestamp(newNote.getTimestamp());
	    	          return noteRepository.save(note);
	    	        })
	    	        .orElseGet(() -> {
	    	          newNote.setId(id);
	    	          return noteRepository.save(newNote);
	    	        });
	}

}