package com.ieireference.system.web;

public interface DownloadListener {

	void DownloadFileChanged(DownloadEvent e);
	void DownloadFileCompleted(DownloadEvent e);
	
}
