package com.ieireference.system.web;

import javax.net.ssl.SSLContext;
/**
 * 
 * Represents a class with Internet Options
 * 
 * */
public final class InternetOptions {
	
	/**
	 * 
	 * A enumeration of protocols
	 * 
	 * */
	public enum ProtocolType {
		/**
		 * Some Protocol SSL
		 * */
		SSL, 
		/**
		 * Protocol SSL 2.0
		 * */
		SSLv2,
		/**
		 * Protocol SSL 3.0
		 * */
		SSLv3,
		/**
		 * Some Protocol TLS
		 * */
		TLS,
		/**
		 * Protocol TLS 1.0
		 * */
		TLSv1,
		/**
		 * Protocol TLS 1.1
		 * */
		TLSv11,
		/**
		 * Protocol TLS 1.2
		 * */
		TLSv12
	}

	
	/**
	 * 
	 * Add protocols to the JVM.
	 * 
	 * @param type The type to add.
	 * 
	 * */
	public static void addProtocols(ProtocolType type) {
		String protocol = "";
		
		if(type == ProtocolType.SSL) protocol = "SSL";
		else if(type == ProtocolType.SSLv2) protocol = "SSLv2";
		else if(type == ProtocolType.SSLv3) protocol = "SSLv3";
		else if(type == ProtocolType.TLS) protocol = "TLS";
		else if(type == ProtocolType.TLSv1) protocol = "TLSv1";
		else if(type == ProtocolType.TLSv11) protocol = "TLSv11";
		else if(type == ProtocolType.TLSv12) protocol = "TLSv12";
		
		try {
	        SSLContext ctx = SSLContext.getInstance(protocol);
	        ctx.init(null, null, null);
	        SSLContext.setDefault(ctx);
		} catch (Exception e) {
	        e.printStackTrace();
		}
		
	}
	
	
}
