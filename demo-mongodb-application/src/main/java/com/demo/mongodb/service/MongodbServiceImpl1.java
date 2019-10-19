package com.demo.mongodb.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MongodbServiceImpl1 implements MongodbService{
	public void display() {
		System.out.println("Inside MongodbServiceImpl1");
	}
}
