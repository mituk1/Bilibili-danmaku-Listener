package com.saki9.barrageCatch.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.saki9.barrageCatch.conf.WebSocketProxy;
import com.saki9.barrageCatch.utils.ByteUtils;

@Service
public class MakeClientService {
	/**
	 * 房间号
	 */
	@Value("${bilibili.roomid}")
	private String roomId;
	/**
	 * WebSocket链接
	 */
	@Value("${bilibili.url}")
	private String url;
	/**
	 * 握手请求头
	 */
	@Value("${bilibili.firstByte}")
	private String firstByte;
	/**
	 * 握手请求体
	 */
	@Value("${bilibili.clientID}")
	private String clientID;
	/**
	 * 心跳包
	 */
	@Value("${bilibili.heartByte}")
	private String heartByte;
	
	public void StartService() throws URISyntaxException, InterruptedException, UnsupportedEncodingException {
		WebSocketProxy client = new WebSocketProxy(url);
		
		firstByte = firstByte.replace("%replce%", Integer.toHexString(clientID.getBytes().length + 16));
        byte[] byte_1 = ByteUtils.hexToByteArray(firstByte);
        byte[] byte_2 = clientID.getBytes("UTF-8");
        byte[] reqCode = ByteUtils.byteMerger(byte_1, byte_2);
        
        client.send(reqCode);
        
        Thread heartThread = new Thread(new HeartByteService(client, heartByte));
        heartThread.start();
	}
	
}
