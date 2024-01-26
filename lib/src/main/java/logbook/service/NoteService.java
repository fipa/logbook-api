package logbook.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logbook.domain.Note;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return (List<Note>) noteRepository.findAll();
    }
        
    public Note findOne(Long id) throws Exception {
        return noteRepository.findById(id).orElseThrow(()->new Exception());
    }

    public Note saveNote(Note note) {
        note.setTimestamp(LocalDateTime.now());
        noteRepository.save(note);
        return note;
    }

	public Map<String, Integer> getWords(String filter) {
		Map<String, Integer> words = new HashMap<String, Integer>();
		
		Iterable<Note> allNotes = noteRepository.findAll();
		for (Note note : allNotes) {
			String[] noteWords = note.getContent().split(" ");
			for (String word : noteWords) {
				if (words.containsKey(word)) {
					words.put(word, words.get(word) + 1);
				} else {
					words.put(word, 1);	
				}
				
			}
		}
		
		return words;
	}



}