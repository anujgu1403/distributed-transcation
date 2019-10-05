package com.spring.batch.demo.util;

import java.io.IOException;
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

	public static String compileAndRunProgram(String programString) {
		System.out.println("Start JavaEditorUtil: compileAndRunProgram :: ");

		String outputResult = null;
		if (null != programString && !programString.isEmpty()) {
			outputResult = getProgramOutputResult(programString);
			System.out.println("Start JavaEditorUtil: compileAndRunProgram :: outputResult: " + outputResult);

		}

		return outputResult;
	}

	public static String getProgramOutputResult(String programString) {
		System.out.println("Start JavaEditorUtil: getCompilationTask :: ");
		URI sourceUri = null;
		String outputResult = null;
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
			}

		}
		return outputResult;
	}

	public static String getInputClassName(String programString) {
		System.out.println("Start JavaEditorUtil: getInputClassName :: programString: " + programString);

		String inputClassName = null;
		if (null != programString && !programString.isEmpty()) {
			int indexOfClasskeyword = programString.indexOf("public class");
			int indexOfFirstCurly = programString.indexOf('{');
			inputClassName = programString.substring(indexOfClasskeyword + 13, indexOfFirstCurly);
			System.out.println("Start JavaEditorUtil: getInputClassName :: inputClassName: " + inputClassName);
		}
		return inputClassName.trim();
	}

	static class StringJavaFileObject extends SimpleJavaFileObject {

		private String content = null;

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
