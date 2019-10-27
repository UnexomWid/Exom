/**
 * Exom-Legacy (https://github.com/UnexomWid/Exom-Legacy)
 *
 * This project is licensed under the MIT license.
 * Copyright (c) 2017-2019 UnexomWid (https://uw.exom.dev)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package Exom.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Contains methods used for Java Class interaction.
 * 
 * @author UnexomWid
 *
 * @since 1.0
 */
public class ClassUtils {

	/**
	 * Compiles a Java Class.
	 * 
	 * @param source The code of the class.
	 * @param path The path where to compile the class to.
	 * @param name The name of the class.
	 * @param ext The extension of the class.
	 * 
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 *
	 * @since 1.0
	 */
	public static void Compile(String source, String path, String name, String ext) throws UnsupportedEncodingException, IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if(compiler == null)
			throw new NullPointerException("Compilation Exception: Exom was not ran with JDK");
		
		if(path.endsWith("/") || path.endsWith("\\"))
			path = path.substring(0, path.length() - 1);
		
		FileOutputStream writef = new FileOutputStream(path + "/" + name + ".java");
		writef.write(source.getBytes(StandardCharsets.UTF_8));
		writef.close();
		
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(path + "/" + name + ".java"));
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
		boolean success = task.call();
		fileManager.close();
		
		File javaFile = new File(path + "/" + name + ".java");
		File classFile = new File(path + "/" + name + ".class");
		File extFile = new File(path + "/" + name + "." + ext);
		
		javaFile.delete();
		
		if(success == false)
			throw new IllegalArgumentException("Compilation Exception: Invalid source code");
		
		if(extFile.exists())
			extFile.delete();
		
		classFile.renameTo(extFile);
	}
	
	/**
	 * Runs a method from a Java Class file.
	 * 
	 * @param classFile The Java Class file.
	 * @param method The method to execute.
	 * @param args The arguments to execute with.
	 * @param argTypes The types of arguments.
	 * 
	 * @return The value returned by the method.
	 * 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 *
	 * @since 1.0
	 */
	public static Object Run(String classFile, String method, Object[] args, java.lang.Class<?>[] argTypes) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return Load(classFile).getMethod(method, argTypes).invoke(null, args);
	}
	
	/**
	 * Runs a method from a Java Class file.
	 * 
	 * @param classFile The Java Class file.
	 * @param className The name of the Java Class.
	 * @param method The method to execute.
	 * @param args The arguments to execute with.
	 * @param argTypes The types of arguments.
	 * 
	 * @return The value returned by the method.
	 * 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 *
	 * @since 1.0
	 */
	public static Object Run(String classFile, String className, String method, Object[] args, java.lang.Class<?>[] argTypes) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		return Load(classFile, className).getMethod(method, argTypes).invoke(null, args);
	}
	
	/**
	 * Loads a Java Class from a file.
	 * 
	 * @param classFile The Java Class file to load from.
	 * 
	 * @return The Java Class.
	 * 
	 * @throws ClassNotFoundException 
	 *
	 * @since 1.0
	 */
	public static java.lang.Class<?> Load(String classFile) throws ClassNotFoundException {
		return new ClassUtils().new ByteLoader().Load(classFile);
	}
	
	/**
	 * Loads a Java Class from a file.
	 * 
	 * @param classFile The Java Class file to load from.
	 * @param className The name of the Java Class.
	 * 
	 * @return The Java Class.
	 * 
	 * @throws ClassNotFoundException 
	 *
	 * @since 1.0
	 */
	public static java.lang.Class<?> Load(String classFile, String className) throws ClassNotFoundException {
		return new ClassUtils().new ByteLoader().Load(classFile, className);
	}
	
	/**
	 * Represents a byte loader that contains methods used for loading Java Classes from bytes.
	 * 
	 * @author UnexomWid
	 *
	 * @since 1.0
	 */
	public class ByteLoader extends ClassLoader {

		/**
		 * Loads a Java Class from a file.
		 * 
		 * @param classFile The Java Class file to load from.
		 * 
		 * @return The Java Class.
		 * 
		 * @throws ClassNotFoundException 
		 *
		 * @since 1.0
		 */
		public java.lang.Class<?> Load(String classFile) throws ClassNotFoundException {
			try {
				Path file = Paths.get(classFile);
				byte[] data = Files.readAllBytes(file);
				String name = file.getFileName().toString();
			    return defineClass(name.substring(0, name.length() - 5), data, 0, data.length);
			}
			catch(Exception ex) {
				throw new ClassNotFoundException();
			}
		}
		
		/**
		 * Loads a Java Class from a file.
		 * 
		 * @param classFile The Java Class file to load from.
		 * @param className The name of the Java Class.
		 * 
		 * @return The Java Class.
		 * 
		 * @throws ClassNotFoundException 
		 *
		 * @since 1.0
		 */
		public java.lang.Class<?> Load(String classFile, String className) throws ClassNotFoundException {
			try {
				Path file = Paths.get(classFile);
				byte[] data = Files.readAllBytes(file);
			    return defineClass(className, data, 0, data.length);
			}
			catch(Exception ex) {
				throw new ClassNotFoundException();
			}
		}
	}
}
