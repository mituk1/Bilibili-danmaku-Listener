package com.saki9.danmaku.conf;

import com.saki9.danmaku.service.MessageHandleService;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;

/**
 * @author 本間Saki
 */
public class WebSocket extends WebSocketClient {

	public WebSocket(String url) throws URISyntaxException {
        super(new URI(url));
    }
 
    @Override
    public void onOpen(ServerHandshake shake) {
    }

    @Override
    public void onMessage(ByteBuffer message) {
        try {
            new MessageHandleService().messageHandle(message);
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
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