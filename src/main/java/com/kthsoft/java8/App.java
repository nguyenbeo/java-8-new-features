package com.kthsoft.java8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class App {
	public static void main(String[] args) {
		System.out.println("Some demos of new features in Java 1.8");

		// create a script engine manager
		ScriptEngineManager factory = new ScriptEngineManager();
		
		// create a JavaScript engine
		ScriptEngine engine = factory.getEngineByName("nashorn");
		
		// run javascript
		runJavascript(engine);
		
		// run javascript file
		runJavascriptFile(engine);
		
		// invoke javascript's function
		invokeJavascriptFunction(engine);
		
		// expose java object in javascript
		exposeJavaObject(engine);
	}

	private static void runJavascript(ScriptEngine engine) {
		try {
			engine.eval("print('Hello world!')");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	private static void runJavascriptFile(ScriptEngine engine) {
		try {
			engine.eval(new FileReader("./src/main/java/com/kthsoft/java8/demo.js"));
		} catch (ScriptException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void invokeJavascriptFunction(ScriptEngine engine) {
		try {
			engine.eval(new FileReader("./src/main/java/com/kthsoft/java8/function.js"));
			
			Invocable inv = (Invocable) engine;
			
	        inv.invokeFunction("invokeMe", "Parameter");
		} catch (ScriptException | FileNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	private static void exposeJavaObject(ScriptEngine engine) {
		try {
			File classFile = new File(App.class.getProtectionDomain().getCodeSource().
					getLocation().getPath());
			
			// expose File object as a global variable to the engine
	        engine.put("file", classFile);
	        
			engine.eval("print(file.getAbsolutePath())");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
