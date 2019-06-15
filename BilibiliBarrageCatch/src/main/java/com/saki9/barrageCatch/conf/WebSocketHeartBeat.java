package com.saki9.barrageCatch.conf;

import com.saki9.barrageCatch.utils.ByteUtils;

public class WebSocketHeartBeat implements Runnable{
	WebSocket client;
	
	//心跳包内容
	String heartbeat = "00000010001000010000000200000001";
	
	public WebSocketHeartBeat(WebSocket client){
		this.client = client;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				// 心跳包发送间隔
				Thread.sleep(30000);
				client.send(ByteUtils.hexToByteArray(heartbeat));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
