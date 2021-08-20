package com.ieireference.system.io;

import java.io.File;

/**
 * Represents a class with access the OS Temp, You can make files or directories in the OS Temp.
 * 
 */
public final class OSTemp {
	
	/**
	 * Make a directory on temp. <br>The directory have the prefix like <code>"myDir"</code> and suffix like <code>"Temp"</code>. Resulting in prefix + suffix (<code>"myDirTemp"</code>). If the directory exist he will be deleted and created.
	 * 
	 * @param prefix the prefix. Is not necessary add '\'
	 * @param someRandomSuffix optional if you want like <code>"myDirNumber62665452"</code>
	 * @param deleteOnExit optional, if You want delete on exit true else false.
	 * 
	 * @return the directory as <code>File</code> created.
	 * */
	public static File createTempDirectory(String prefix, String someRandomSuffix, boolean deleteOnExit) {
		
		File file = new File(getOSTemp() + "\\" + prefix + someRandomSuffix);
		if(file.exists()) {
			file.delete();
			file.mkdir();
		} else file.mkdir();
		if(deleteOnExit) {
			file.deleteOnExit();
		}
		return file;
	}
	
	/**
	 * 
	 * @return If not fail, returns the OS Temp Directory.
	 * */
	public static String getOSTemp() {
		// TODO Auto-generated method stub
		return System.getProperty("java.io.tmpdir");
	}
	
}
