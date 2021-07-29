package com.ieireference.system.web;

import javax.net.ssl.SSLContext;

public final class InternetOptions {
	
	public enum ProtocolType {
		SSL,
		SSLv2,
		SSLv3,
		TLS,
		TLSv1,
		TLSv11,
		TLSv12
	}

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
