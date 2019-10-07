package com.spring.batch.demo.publication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping("/add")
	public ResponseEntity<?> addAuthor(@RequestBody Author author) {
		Author authorSaved = authorService.addAuthor(author);
		return new ResponseEntity<Author>(authorSaved, HttpStatus.CREATED);
	}
}
