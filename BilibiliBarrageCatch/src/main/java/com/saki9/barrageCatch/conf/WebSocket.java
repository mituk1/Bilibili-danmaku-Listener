package com.saki9.barrageCatch.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saki9.barrageCatch.service.BarrageCatchService;

@Component
public class WebSocket extends WebSocketClient {
	@Autowired
	private BarrageCatchService barrageCatchServiceimpl;
	
	public WebSocket(String url) throws URISyntaxException {
        super(new URI(url));
    }
 
    @Override
    public void onOpen(ServerHandshake shake) {
    	
    }

    @Override
    public void onMessage(ByteBuffer message) {
    	// barrageCatchServiceimpl.GetMessage(message);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        System.out.println("链接已关闭");
    }
 
    @Override
    public void onError(Exception e) {
        System.out.println(e);
    }
    
    @Override
    public void onMessage(String message) {
    } 
}