package com.saki9.barrageCatch.service;

import com.saki9.barrageCatch.conf.WebSocket;
import com.saki9.barrageCatch.utils.ByteUtils;

public class HeartByteService implements Runnable{
	WebSocket client;
	String heartByte;
	
	public HeartByteService(WebSocket client, String heartByte){
		this.client = client;
		this.heartByte = heartByte;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(30000);
				client.send(ByteUtils.hexToByteArray(heartByte));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
