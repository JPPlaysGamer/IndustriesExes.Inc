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

public final class WebClient {
	
	private DownloadListener DownloadProgress;
	
	private Thread currentThread;
	
	public WebClient() {}
	public WebClient(DownloadListener progress) {
		
		setDownloadProgress(progress);
	}
	
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
	
	public void setDownloadProgress(DownloadListener downloadProgress) throws IllegalStateException{
		
		if(!IsThreadAlive()) {
			DownloadProgress = downloadProgress;
		}else {
			throw new IllegalStateException("You can't add because this object is running.");
		}
	}
	
	public void DownloadFileAsync(URL file, String output) throws IllegalStateException, FileAlreadyExistsException{
		
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
			
			@Override
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
					e.printStackTrace();
					event.Error = true;
					DownloadProgress.DownloadFileCompleted(event);
				}
				
			}
		});
		currentThread.start();
	}
	
	@SuppressWarnings("deprecation")
	public void DownloadThreadStop() throws IllegalStateException{
		if(currentThread != null) {
			if(currentThread.isAlive()) {
				currentThread.stop();
			}
			else throw new IllegalStateException("The Download Thread is null or not Alive");
		}
		else throw new IllegalStateException("The Download Thread is null or not Alive");
	}
	
	public boolean IsThreadAlive() {
		return (currentThread != null) ? currentThread.isAlive() : false;
	}
}
