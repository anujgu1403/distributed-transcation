package com.spring.batch.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.batch.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
