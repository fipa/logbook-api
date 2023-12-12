package logbook.service;

import org.springframework.data.repository.CrudRepository;

import logbook.domain.Note;

public interface NoteRepository extends CrudRepository<Note,Long> {
}
