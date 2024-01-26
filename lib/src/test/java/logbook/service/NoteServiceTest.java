package logbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import logbook.domain.Note;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class NoteServiceTest {

    private ArrayList<Note> notes;

    @Mock
    private NoteRepository noteRepositoryMock;

    private NoteService noteService;
    private Note n1;
    private Note n2;
    private Note n3;

    @BeforeEach
    public void setup() {
        notes = new ArrayList<>();
        n1 = new Note();
        n1.setId(1L);
        n1.setTitle("firstNote");
        n1.setContent("First note, with words java spring automation.");

        n2 = new Note();
        n2.setId(2L);
        n2.setTitle("secondNote");
        n2.setContent("Second note: with words java spring integration.");

        n3 = new Note();
        n3.setId(3L);
        n3.setTitle("thirdNote");
        n3.setContent("Third note! with words java, any more?");

        notes.add(n1);
        notes.add(n2);
        notes.add(n3);

        when(noteRepositoryMock.findAll()).thenReturn(notes);
        when(noteRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(n1));
        when(noteRepositoryMock.findById(2L)).thenReturn(Optional.ofNullable(n2));

        noteService = new NoteService();
        noteService.setNoteRepository(noteRepositoryMock);
    }
    
    @Test
    public void findOneLanzaExcepcionCuandoElIdNoSeEncuentra()
    {
    	assertThrows(Exception.class, () -> noteService.findOne(10L));
    }
    
    @Test
    public void testAlCrearLaPrimeraNotaCadaPalabraEnElDiccionarioApareceUnaVez() {
    	
        Note n = new Note();
        n.setId(1L);
        n.setTitle("firstNote");
        n.setContent("First note, with words java spring automation.");
    	
    	when(noteRepositoryMock.findAll()).thenReturn(Arrays.asList(n));
    	
   		Map<String, Integer> expected = new HashMap<String, Integer>();
		expected.put("First", 1);
		expected.put("note", 1);
		expected.put("with", 1);
		expected.put("words", 1);
		expected.put("java", 1);
		expected.put("spring", 1);
		expected.put("automation", 1);
  	  	
		Map<String, Integer> words = noteService.getWords();
    	assertEquals(expected.get("First"), words.get("First"));
	
    }
    
    @Test
    public void testAlCrearUnaNotaConPalabrasRepetidasElContadorSeIncrementa() {
    	
        Note n = new Note();
        n.setId(1L);
        n.setTitle("firstNote");
        n.setContent("First note, with words java java spring automation.");
    	
    	when(noteRepositoryMock.findAll()).thenReturn(Arrays.asList(n));
    	
		Map<String, Integer> words = noteService.getWords();
    	assertEquals(2, words.get("java"));
	
    }

}