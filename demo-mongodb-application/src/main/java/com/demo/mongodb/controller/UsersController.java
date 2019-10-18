package com.demo.mongodb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mongodb.model.Users;
import com.demo.mongodb.repository.UsersRepository;

@RestController
@RequestMapping("/api/users/all")
public class UsersController {

	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping
	public List<Users> getAllUsers(){
		return usersRepository.findAll();
	}
}
