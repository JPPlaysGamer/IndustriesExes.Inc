package com.ieireference.system;

/**
 * Represents a new utilities for use in string.
 * */
public final class StringUtil {
	/**Check if a String is null or empty
	 * 
	 * @param s The string to check.
	 * 
	 * @return If null or length() == 0 true else false.
	 * */
	public static boolean isEmptyOrNull(String s) {
        return s == null || s.length() == 0;
    }
	
	/**
	 * Finds a occurrence in a string. For example:<br><br>
	 * <code>
	 * 		String test = "GDHYFFGGHBFGG"; <br>
	 * 		System.out.println(StringUtil.countMatches(test, "G")); <br>//Prints a 5 letter 'G' found.
	 * 
	 * </code>
	 * 
	 * @param text The Text to find a substring.
	 * @param str The substring to find.
	 * 
	 * @return The number of substrings found.
	 * */
	public static int countMatches(String text, String str)
    {
        if (isEmptyOrNull(text) || isEmptyOrNull(str)) {
            return 0;
        }
 
        int index = 0, count = 0;
        while (true)
        {
            index = text.indexOf(str, index);
            if (index != -1)
            {
                count ++;
                index += str.length();
            }
            else {
                break;
            }
        }
 
        return count;
    }
}
