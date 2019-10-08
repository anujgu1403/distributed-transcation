package com.java.editor.service;

import org.springframework.stereotype.Service;
import com.java.editor.util.JavaEditorUtil;

@Service
public class JavaEditorServiceImpl implements JavaEditorService{

	@Override
	public String compileAndRunProgram(String inputProgramString) throws Exception {
		System.out.println("Start JavaEditorServiceImpl: compileAndRunProgram:: ");
		String programOutput = "";
		if(null !=inputProgramString && !inputProgramString.isEmpty()) {
			programOutput = JavaEditorUtil.compileAndRunProgram(inputProgramString);
			System.out.println("Start JavaEditorServiceImpl: compileAndRunProgram:: programOutput: "+programOutput);
		}
		return programOutput;
	}

	
}
