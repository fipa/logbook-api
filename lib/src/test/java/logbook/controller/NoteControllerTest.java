package logbook.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import logbook.service.NoteService;

public class NoteControllerTest {

    private NoteController noteController;
	
    private NoteService noteService;
  
    @Test
    public void testAllNotesLlamaAServiceFindAll() {
        noteService = mock(NoteService.class);
        noteController = new NoteController(noteService);
		
        noteController.allNotes();
		
        verify(noteService).findAll();

    }
}