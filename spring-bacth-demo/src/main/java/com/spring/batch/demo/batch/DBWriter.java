package com.spring.batch.demo.batch;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.spring.batch.demo.model.User;
import com.spring.batch.demo.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<User> {

	@Autowired
	UserRepository userRepository;

	@Override
	public void write(List<? extends User> users) throws Exception {
		System.out.println("Saving users: " + users);
		userRepository.saveAll(users);
	}
}
