package com.ieireference.system;

/**
 * Represents a Exception for String. <br> This Exception is used when a CharSequence is not found in a String.contains();
 * */
public class CharSequenceNotFoundException extends Exception {

	private static final long serialVersionUID = -5757566573349295815L;

	public CharSequenceNotFoundException(String s) {
		super(s);
	}

}
