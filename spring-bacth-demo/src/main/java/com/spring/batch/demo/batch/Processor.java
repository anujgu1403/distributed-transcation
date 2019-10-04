package com.spring.batch.demo.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.spring.batch.demo.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {

	private static final Map<String, String> DEPT_NAMES = new HashMap<String, String>();

	public Processor() {
		DEPT_NAMES.put("01", "IT");
		DEPT_NAMES.put("02", "HR");
		DEPT_NAMES.put("03", "ADMIN");
	}

	@Override
	public User process(User user) throws Exception {
		user.setDept(DEPT_NAMES.get(user.getDept()));
		return user;
	}
}
