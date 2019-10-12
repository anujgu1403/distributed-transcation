package com.java.editor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.editor.service.JavaEditorService;

/**
 * @author anuj kumar
 * 
 *         This class is main controller which exposes end point java editor api
 *
 */
@RestController
@RequestMapping("/api/java")
public class JavaEditorController {

	private static final Logger logger = LoggerFactory.getLogger(JavaEditorController.class);

	@Autowired
	private JavaEditorService javaEditorService;

	/**
	 * compileAndRunProgram method is to invoke service level method to execute
	 * business logic
	 * 
	 * @param String
	 * @return {@link ResponseEntity}
	 *
	 */
	@PostMapping("/editor")
	public ResponseEntity<?> compileAndRunProgram(@RequestBody String inputProgramString) throws Exception {
		logger.info("Start JavaEditorController: compileAndRunProgram:: inputProgramString: " + inputProgramString);
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
