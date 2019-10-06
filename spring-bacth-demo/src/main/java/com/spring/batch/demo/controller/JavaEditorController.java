package com.spring.batch.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/java")
public class JavaEditorController {

	@Autowired
	private JavaEditorService javaEditorService;

	@PostMapping("/editor")
	public ResponseEntity<?> compileAndRunProgram(@RequestBody String inputProgramString) throws Exception {
		System.out.println(
				"Start JavaEditorController: compileAndRunProgram:: inputProgramString: " + inputProgramString);
		ResponseEntity<String> responseEntity = null;
		String programOutput = javaEditorService.compileAndRunProgram(inputProgramString);
		if (!programOutput.isEmpty()) {
			responseEntity = new ResponseEntity<String>(programOutput, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("Service is unavailable", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

}
