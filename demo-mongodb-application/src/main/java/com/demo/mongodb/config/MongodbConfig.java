package com.demo.mongodb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.demo.mongodb.model.Users;
import com.demo.mongodb.repository.UsersRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = UsersRepository.class)
public class MongodbConfig {

	@Bean
	CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
		return users ->{
			usersRepository.save(new Users(1, "John", 1000));
			usersRepository.save(new Users(2, "Sam", 2000));
			usersRepository.save(new Users(3, "Suma", 3000));
		};
	}
}
