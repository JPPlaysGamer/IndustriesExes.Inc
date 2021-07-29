package com.ieireference.system.web;

public final class DownloadEvent {
	
	boolean Completed;
	boolean Error;
	
	long BytesTotal;
	long BytesIn;
	
	int Percentage;
	
	public boolean Completed() { return Completed;}
	public boolean Error() { return Error;}
	
	public long BytesTotal() { return BytesTotal;}
	public long BytesIn() { return BytesIn;}
	
	public int Percentage() { return Percentage;}
}
