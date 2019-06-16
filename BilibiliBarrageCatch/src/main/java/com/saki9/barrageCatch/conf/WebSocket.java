package com.saki9.barrageCatch.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.saki9.barrageCatch.utils.PrintUtils;

public class WebSocket extends WebSocketClient {
	public WebSocket(String url) throws URISyntaxException {
        super(new URI(url));
    }
 
    @Override
    public void onOpen(ServerHandshake shake) {
    	
    }

    @Override
    public void onMessage(ByteBuffer message) {
    	PrintUtils.ByteBufferToString(message);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        System.out.println("Closed");
    }
 
    @Override
    public void onError(Exception e) {
        System.out.println(e);
    }
    
    @Override
    public void onMessage(String message) {
    } 
}