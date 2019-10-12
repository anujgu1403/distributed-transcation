package com.java.editor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author anuj kumar
 * 
 *         This is util class which is build to execute all re-usable methods
 *
 */
public class JavaEditorUtil {

	private static final Logger logger = LoggerFactory.getLogger(JavaEditorUtil.class);

	/**
	 * This method is to compile and execute program
	 * 
	 * @param String
	 * @param String
	 * @return String
	 *
	 */
	public static String getProgramOutputResult(String programString, String systemJdkPath) throws Exception {
		logger.info("Start JavaEditorUtil: getCompilationTask :: systemJdkPath: " + systemJdkPath);

		URI sourceUri = null;
		String outputResult = "";
		CompilationTask compilationTask = null;

		if (null != programString && !programString.isEmpty()) {

			// Set system jdk path
			System.setProperty(JavaEditorConstants.JAVA_HOME, systemJdkPath);
			DiagnosticCollector<JavaFileObject> diagnosticsCollector = new DiagnosticCollector<JavaFileObject>();
			final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(diagnosticsCollector, null,
					null);

			// To create java source file
			sourceUri = URI.create(getInputClassName(programString) + JavaFileObject.Kind.SOURCE.extension);
			JavaEditorUtil.StringJavaFileObject fileObject = new JavaEditorUtil.StringJavaFileObject(sourceUri,
					JavaFileObject.Kind.SOURCE);
			fileObject.setContent(programString);

			// put javaFileObject into list
			Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(fileObject);

			// put them into task
			compilationTask = javaCompiler.getTask(null, javaFileManager, diagnosticsCollector, null, null,
					fileObjects);

			boolean result = compilationTask.call();
			if (result) {

				logger.info("JavaEditorUtil: compileAndRunProgram :: compiled successfully!");

				// run the program's .class file and send output result as string
				String command = JavaEditorConstants.JAVA_RUN_CMD + getInputClassName(programString);
				outputResult = runProcess(command);

			} else {

				List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticsCollector.getDiagnostics();
				for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {

					// read error details from the diagnostic object and return as string output
					outputResult = outputResult + diagnostic.getMessage(null);
				}

			}
		}
		return outputResult;
	}

	/**
	 * @param String
	 * @return String
	 *
	 *         This method is to get class name which is having main method
	 */
	public static String getInputClassName(String programString) {
		logger.info("Start JavaEditorUtil: getInputClassName :: programString: " + programString);

		String inputClassName = "";
		if (null != programString && !programString.isEmpty()) {
			int indexOfClasskeyword = programString.indexOf(JavaEditorConstants.PUBLIC_CLASS);
			int indexOfFirstCurly = programString.indexOf(JavaEditorConstants.FIRST_CURLY_BRACE);
			inputClassName = programString.substring(indexOfClasskeyword + JavaEditorConstants.CLASS_NAME_START_INDEX, indexOfFirstCurly);
			logger.info("JavaEditorUtil: getInputClassName :: inputClassName: " + inputClassName);
		}
		return inputClassName.trim();
	}

	/**
	 * @param String
	 * @return String
	 *
	 *         This method is execute the compiled class file and return the output
	 *         as string
	 */
	private static String runProcess(String command) throws Exception {
		logger.info("Start JavaEditorUtil: runProcess :: command: " + command);
		String outputResult = "";
		String line = "";

		// To run the compiled code
		Process process = Runtime.getRuntime().exec(command);
		if (process.getInputStream() != null) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				outputResult = outputResult + line;
			}
		} else {
			// To process the error stream
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = bufferedReader.readLine()) != null) {
				outputResult = outputResult + line;
			}
		}
		return outputResult;
	}

	/**
	 * @author anuj kumar
	 * 
	 *         This is static inner class to process as String java file object
	 *
	 */
	static class StringJavaFileObject extends SimpleJavaFileObject {

		private String content = "";

		protected StringJavaFileObject(URI uri, Kind kind) {
			super(uri, kind);
		}

		public void setContent(String content) {
			this.content = content;
		}

		public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
			return content;
		}
	}
}
