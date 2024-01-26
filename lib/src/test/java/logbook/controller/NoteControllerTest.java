package logbook.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.List;

import org.junit.jupiter.api.Test;
import logbook.domain.Note;
import logbook.service.NoteService;

public class NoteControllerTest {

    private NoteController noteController;
	
    private NoteService noteService;
  
    @Test
    public void testFindAll() {
        noteService = mock(NoteService.class);
        noteController = new NoteController(noteService);
		List<Note> result = noteController.allNotes();
		verify(noteService).findAll();

    }
}