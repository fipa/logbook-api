package logbook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import logbook.domain.Note;
import logbook.service.NoteRepository;
import logbook.service.NoteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

}