package com.ieireference.system.web;

/**
 * 
 * Represents a interface for methods of events in WebClient
 * 
 * @see DownloadEvent
 * @see WebClient
 * 
 * */
public interface DownloadListener {

	/**
	 * 
	 * This event is used when downloading a file your state is changed.
	 * 
	 * @param e Get event status.
	 * */
	void DownloadFileChanged(DownloadEvent e);
	/**
	 * 
	 * This event is used when downloading a file is completed or caused a error.
	 * 
	 * @param e Get event status.
	 * */
	void DownloadFileCompleted(DownloadEvent e);
	
}
