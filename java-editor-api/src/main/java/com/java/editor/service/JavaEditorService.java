package com.java.editor.service;

import java.util.Map;

/**
 * @author anuj kumar
 * 
 * This service level interface to declare the method
 *
 */
public interface JavaEditorService {

	public Map<String, String> compileAndRunProgram(String inputProgramString) throws Exception;
}
