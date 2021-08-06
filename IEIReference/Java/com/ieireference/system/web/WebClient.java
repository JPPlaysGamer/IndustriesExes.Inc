package com.ieireference.system.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

/**
 * Represents a client for web. Contains functions to download file asynchronous and a thread for this. Events can be used for Java Forms like download file with JProgressBar.
 * 
 * @see DownloadListener
 * @see DownloadEvent
 * */
public final class WebClient {
	
	private DownloadListener DownloadProgress;
	
	private Thread currentThread;
	
	public WebClient() {}
	/**
	 * 
	 * Initializes and set events
	 * 
	 * @param progress Add the events for {@link #DownloadFileAsync(URL, String)}
	 * */
	public WebClient(DownloadListener progress) {
		
		setDownloadProgress(progress);
	}
	
	/**
	 * 
	 * Get a file size from web.
	 * 
	 * @param url The URL to get the size.
	 * @return The size.
	 * */
	public static long getFileSizeFromNet(URL url) {
	    URLConnection conn = null;
	    try {
	        conn = url.openConnection();
	        if(conn instanceof HttpURLConnection) {
	            ((HttpURLConnection)conn).setRequestMethod("HEAD");
	        }
	        conn.getInputStream();
	        return conn.getContentLength();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    } finally {
	        if(conn instanceof HttpURLConnection) {
	            ((HttpURLConnection)conn).disconnect();
	        }
	    }
	}
	
	/**
	 * 
	 * Sets manually events.
	 * 
	 * @param downloadProgress Add the events for {@link #DownloadFileAsync(URL, String)}
	 * 
	 * @throws IllegalStateException If call this function when {@link #DownloadFileAsync(URL, String)} is running.
	 * */
	public void setDownloadProgress(DownloadListener downloadProgress) throws IllegalStateException{
		
		if(!IsThreadAlive()) {
			DownloadProgress = downloadProgress;
		}else {
			throw new IllegalStateException("You can't add because this object is running.");
		}
	}
	
	/**
	 * 
	 * Start asynchronous download. Is needed set events before of start.
	 * 
	 * @param file The file to Download.
	 * @param output The output of file download.
	 * 
	 * @throws IllegalStateException If you call when running or Events are null.
	 * @throws FileAlreadyExistsException If you try call with a file existent in output
	 * */
	public void DownloadFileAsync(final URL file, final String output) throws IllegalStateException, FileAlreadyExistsException{
		
		if(new File(output).exists()) {
			throw new FileAlreadyExistsException("The file '" + output + "' exist and can't download with this existent");
		}
		if(IsThreadAlive()) {
			throw new IllegalStateException("You can't call DownloadFileAsync with this object running, wait thread ends.");
		}
		if(DownloadProgress == null) {
			throw new IllegalStateException("DownloadListener is null");
		}
		
		currentThread = new Thread(new Runnable() {
			
			public void run() {
				DownloadEvent event = new DownloadEvent();
				try {
					long byteT = getFileSizeFromNet(file);
					
					
					BufferedInputStream fromNet = new BufferedInputStream(file.openStream());
					FileOutputStream fileOutputStream = new FileOutputStream(output);
					byte[] buffer = new byte[1024];
					int bytecounter = 0;
					
					while((bytecounter = fromNet.read(buffer, 0, 1024)) != -1) {
						
						fileOutputStream.write(buffer, 0, bytecounter);
						
						event.BytesTotal = byteT;
						event.BytesIn = Files.size(new File(output).toPath());
						double percen = (double)event.BytesIn / (double)event.BytesTotal * 100;
						event.Percentage = (int) (Math.floor(percen * 100) / 100);
						
						DownloadProgress.DownloadFileChanged(event);
						
					}
					
					fileOutputStream.close();
					fromNet.close();
					
					event.Completed = true;
					DownloadProgress.DownloadFileCompleted(event);
								
					
				} catch (IOException e) {
					event.Error = true;
					DownloadProgress.DownloadFileCompleted(event);
					e.printStackTrace();
				}
				
			}
		});
		currentThread.start();
	}
	
	
	/**
	 * 
	 * CAUTION. This method stop the download thread, please use if need.
	 * 
	 * Thread.stop() is Deprecated.
	 * 
	 * @throws IllegalStateException If thread is null or not alive when call this.
	 * */
	@Deprecated
	//@SuppressWarnings("deprecation")
	public void DownloadThreadStop() throws IllegalStateException{
		if(currentThread != null) {
			if(currentThread.isAlive()) {
				currentThread.stop();
			}
			else throw new IllegalStateException("The Download Thread is null or not Alive");
		}
		else throw new IllegalStateException("The Download Thread is null or not Alive");
	}
	
	
	/**
	 * 
	 * @return If thread not null, return true or false if alive else false.
	 * */
	public boolean IsThreadAlive() {
		return (currentThread != null) ? currentThread.isAlive() : false;
	}
}
