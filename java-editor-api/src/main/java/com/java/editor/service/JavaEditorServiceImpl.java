package com.java.editor.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.java.editor.util.JavaEditorUtil;

/**
 * @author anuj kumar
 * 
 *         This is an service level impl class for JavaEditorService interface
 *
 */
@Service
public class JavaEditorServiceImpl implements JavaEditorService {

	private static final Logger logger = LoggerFactory.getLogger(JavaEditorServiceImpl.class);

	/**
	 * @param String
	 * @return Map<String, String>
	 * 
	 *         compileAndRunProgram method is to call util class method to compile
	 *         and run the program
	 *
	 */
	
	@Value("${system.jdk.path}")
	private String systemJdkPath;
	
	@Override
	public Map<String, String> compileAndRunProgram(String inputProgramString) throws Exception {
		logger.info("Start JavaEditorServiceImpl: compileAndRunProgram:: ");
		Map<String, String> resultMap = new HashMap<String, String>();
		if (null != inputProgramString && !inputProgramString.isEmpty()) {
			resultMap = JavaEditorUtil.getProgramOutputResult(inputProgramString, systemJdkPath);
			logger.info("Start JavaEditorServiceImpl: compileAndRunProgram:: programOutput: " + resultMap.values());
		}
		return resultMap;
	}

}
