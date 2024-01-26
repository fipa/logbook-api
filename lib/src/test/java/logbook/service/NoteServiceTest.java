package logbook.service;

import java.util.ArrayList;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import logbook.domain.Note;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    private ArrayList<Note> notes;

    @Mock
    private NoteRepository noteRepositoryMock;

    @InjectMocks
    private NoteService noteService;

}