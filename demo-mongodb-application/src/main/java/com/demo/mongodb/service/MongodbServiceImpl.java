package com.demo.mongodb.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("mongodbServiceImpl")
public class MongodbServiceImpl implements MongodbService{
	public void display() {
		System.out.println("Inside MongodbServiceImpl");
	}
}
