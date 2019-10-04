package com.spring.batch.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;


@Data
@Entity
public class User {

	@Id
	private int id;
	private String name;
	private String dept;
	private double salary;
}
