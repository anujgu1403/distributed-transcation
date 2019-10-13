package com.java.editor.controller;

import java.util.HashMap;
import java.util.Map;
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
import com.java.editor.util.JavaEditorConstants;

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
	public ResponseEntity<?> compileAndRunProgram(@RequestBody String inputProgramString) {
		logger.info("Start JavaEditorController: compileAndRunProgram:: inputProgramString: " + inputProgramString);
		ResponseEntity<Map<String, String>> responseEntity = null;
		Map<String, String> resultMap = new HashMap<String, String>();
       
		if (null == inputProgramString || inputProgramString.isEmpty()) {
			resultMap.put(JavaEditorConstants.INVALID_REQUEST, JavaEditorConstants.INVALID_REQUEST_MESSAGE);
			responseEntity = new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.BAD_REQUEST);
		} else {
			try {

				resultMap = javaEditorService.compileAndRunProgram(inputProgramString);
				if (resultMap != null) {
					responseEntity = new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
				}

			} catch (Exception e) {
				logger.info(
						"JavaEditorController: compileAndRunProgram:: Error occured while compiling and running the program:",
						e.getCause());
				resultMap.put(JavaEditorConstants.EXECPTION, JavaEditorConstants.EXECPTION_RESPONSE_MESSAGE);
				responseEntity = new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return responseEntity;
	}
}
