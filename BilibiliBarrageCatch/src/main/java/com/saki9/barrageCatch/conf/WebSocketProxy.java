package com.saki9.barrageCatch.conf;

import java.net.URISyntaxException;

public class WebSocketProxy extends WebSocket{

	public WebSocketProxy(String url) throws URISyntaxException, InterruptedException {
		super(url);
		System.out.println("Loading");
		super.connectBlocking();
        System.out.println("success");
	}

}
