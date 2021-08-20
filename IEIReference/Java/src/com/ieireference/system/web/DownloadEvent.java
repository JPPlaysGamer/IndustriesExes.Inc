package com.ieireference.system.web;
/**
 * 
 * Represents a class of arguments of events in WebClient
 * 
 * @see DownloadListener
 * @see WebClient
 * 
 * */
public final class DownloadEvent {
	
	boolean Completed;
	boolean Error;
	
	long BytesTotal;
	long BytesIn;
	
	int Percentage;
	
	/**
	 * 
	 * @return Get if download completed.
	 * 
	 * */
	public boolean Completed() { return Completed;}
	/**
	 * 
	 * 
	 * @return Get If download error.
	 * 
	 * */
	public boolean Error() { return Error;}
	
	/**
	 * 
	 * @return Get total bytes from file downloading.
	 * */
	public long BytesTotal() { return BytesTotal;}
	/**
	 * 
	 * @return Get bytes writhed from Web.
	 * 
	 * */
	public long BytesIn() { return BytesIn;}
	
	/**
	 * 
	 * @return Get the percentage of file download progress.
	 * */
	public int Percentage() { return Percentage;}
}
