package logbook.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import logbook.service.NoteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DictionaryController {

	@Autowired
	private NoteService noteService;

	@GetMapping("dictionary")
	public Map<String, Integer> getWords() {
		return noteService.getWords();
	}


}