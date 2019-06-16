package com.saki9.barrageCatch.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.saki9.barrageCatch.conf.WebSocket;
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
	 * 握手包
	 */
	@Value("${bilibili.clientID}")
	private String clientID;
	/**
	 * 心跳包
	 */
	@Value("${bilibili.heartByte}")
	private String heartByte;
	
	public void StartService() throws URISyntaxException, InterruptedException, UnsupportedEncodingException {
		WebSocket client = new WebSocket(url);
		System.out.println("Loading");
        client.connectBlocking();
        System.out.println("success");
        
        String joinRequestCode = "000000" + Integer.toHexString(clientID.getBytes().length + 16) + "001000010000000700000001";
        
        byte[] byte_1 = ByteUtils.hexToByteArray(joinRequestCode);
        byte[] byte_2 = clientID.getBytes("UTF-8");
        client.send(ByteUtils.byteMerger(byte_1, byte_2));
        
        Thread heartThread = new Thread(new HeartByteService(client, heartByte));
        heartThread.start();
	}
	
}
