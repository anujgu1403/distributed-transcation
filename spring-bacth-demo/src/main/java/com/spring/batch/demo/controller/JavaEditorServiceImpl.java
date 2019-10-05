package com.spring.batch.demo.controller;

import org.springframework.stereotype.Service;
import com.spring.batch.demo.util.JavaEditorUtil;

@Service
public class JavaEditorServiceImpl implements JavaEditorService{

	@Override
	public String compileAndRunProgram(String inputProgramString) {
		System.out.println("Start JavaEditorServiceImpl: compileAndRunProgram:: ");
		String programOutput = "";
		if(null !=inputProgramString && !inputProgramString.isEmpty()) {
			programOutput = JavaEditorUtil.compileAndRunProgram(inputProgramString);
			System.out.println("Start JavaEditorServiceImpl: compileAndRunProgram:: programOutput: "+programOutput);
		}
		return programOutput;
	}

	
}
