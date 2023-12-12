package logbook.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import logbook.domain.Note;
import logbook.service.NoteService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class NoteControllerTest {

	@InjectMocks
    private NoteController noteController;
	
    @Mock
    private NoteService noteService;
  
    @Test
    public void testFindAll() {
        Note note1 = new Note(1L,"Title",LocalDateTime.now(),"Content");
        Note note2 = new Note(2L,"Title",LocalDateTime.now(),"Content");
        
        List<Note> notes = new ArrayList<Note>();
        notes.add(note1);
        notes.add(note2);

        when(noteService.findAll()).thenReturn(notes);

		List<Note> result = noteController.allNotes();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getTitle()).isEqualTo(note1.getTitle());
        assertThat(result.get(1).getTitle()).isEqualTo(note2.getTitle());
    }
}