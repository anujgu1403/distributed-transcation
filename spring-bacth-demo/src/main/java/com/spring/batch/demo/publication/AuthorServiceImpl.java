package com.spring.batch.demo.publication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public Author addAuthor(Author author) {
		return authorRepository.save(author);
	}

}
