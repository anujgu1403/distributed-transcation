package com.demo.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Users {
	
	@Id
	private int id;
	private String name;
	private int salary;
	
	public Users(int id, String name, int salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
}
