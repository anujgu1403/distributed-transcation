package com.spring.batch.demo.publication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {
	
	@Autowired
	PublicationRepository publicationRepository;
	
	@Override
	public Publication addPublication(Publication publication) {
		Publication publicationSaved = publicationRepository.save(publication);
		return publicationSaved;
	}
}
