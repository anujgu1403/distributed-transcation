package com.spring.batch.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaEditorUtil {

	public static String compileAndRunProgram(String programString) throws Exception {
		System.out.println("Start JavaEditorUtil: compileAndRunProgram :: ");

		String outputResult = "";
		if (null != programString && !programString.isEmpty()) {
			outputResult = getProgramOutputResult(programString);
			System.out.println("Start JavaEditorUtil: compileAndRunProgram :: outputResult: " + outputResult);

		}

		return outputResult;
	}

	public static String getProgramOutputResult(String programString) throws Exception {
		System.out.println("Start JavaEditorUtil: getCompilationTask :: ");
		URI sourceUri = null;
		String outputResult = "";
		CompilationTask compilationTask = null;
		if (null != programString && !programString.isEmpty()) {
			System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.8.0_191");
			DiagnosticCollector<JavaFileObject> diagnosticsCollector = new DiagnosticCollector<JavaFileObject>();
			final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(diagnosticsCollector, null,
					null);
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

			if (result)
				System.out.println("Start JavaEditorUtil: compileAndRunProgram :: compiled successfully!");

			if (!result) {

				List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticsCollector.getDiagnostics();
				for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
					// read error details from the diagnostic object
					outputResult = outputResult + diagnostic.getMessage(null);
				}
			}else {
				String command = "java -cp src ./spring-bacth-demo/"+getInputClassName(programString);
				runProcess(command);
			}

		}
		return outputResult;
	}

	public static String getInputClassName(String programString) {
		System.out.println("Start JavaEditorUtil: getInputClassName :: programString: " + programString);

		String inputClassName = "";
		if (null != programString && !programString.isEmpty()) {
			int indexOfClasskeyword = programString.indexOf("public class");
			int indexOfFirstCurly = programString.indexOf('{');
			inputClassName = programString.substring(indexOfClasskeyword + 13, indexOfFirstCurly);
			System.out.println("Start JavaEditorUtil: getInputClassName :: inputClassName: " + inputClassName);
		}
		return inputClassName.trim();
	}

	private static void printLines(String command, InputStream inputStream) throws Exception {
		String line = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(command + " " + line);
		}
	}

	private static void runProcess(String command) throws Exception {
		System.out.println("Start JavaEditorUtil: runProcess :: command: " + command);
		Process pro = Runtime.getRuntime().exec(command);
		printLines(command + " stdout:", pro.getInputStream());
		printLines(command + " stderr:", pro.getErrorStream());
		pro.waitFor();
		System.out.println(command + " exitValue() " + pro.exitValue());
	}

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
