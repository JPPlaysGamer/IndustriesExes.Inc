package com.ieireference.system.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class of utilities for the jar file in classpath system.
 * */
public final class JarUtils{
	
	protected JarUtils() {}
	
	/**
	 * Read file text lines from classpath as stream. In stream is used:<br><br>
	 * <code>
	 * InputStream file = SomeClassName.class.getResourceAsStream("/path/to/file.txt");<br>
	 * JarUtils.ReadAllLinesClassPath(file, Charset.defaultCharset();
	 * </code>
	 * 
	 * @param streamInClasspath The <code>InputStream</code> of file to read.
	 * @param cs The <code>Charset</code> to read the file. For default use <code>Charset.defaultCharset()</code>
	 * 
	 * @return A <code>List</code> with all the lines of file.
	 * 
	 * @throws IOException - If an I/O error occurs
	 *
	 * */
	public static List<String> ReadAllLinesClassPath(InputStream streamInClasspath, Charset cs) throws IOException{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(streamInClasspath, cs));
		List<String> text = new ArrayList<>();
		
		String ln;
		while((ln = reader.readLine()) != null) {
			text.add(ln);
		}
		
		
		return text;
		
	}
	
}
