package com.saki9.danmaku.service;

import com.saki9.danmaku.conf.WebSocket;
import com.saki9.danmaku.utils.ByteUtils;
import com.saki9.danmaku.utils.ConfigProperties;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 本間Saki
 */
public class MakeClientService {

	private final String url;

	private final String clientHead;

	private final String clientBody;

	private final String heartByte;

	public MakeClientService() {
		url = ConfigProperties.getProperty("url");

		heartByte = ConfigProperties.getProperty("heartByte");

		clientBody = ConfigProperties.getProperty("clientBody")
			.replace("{roomId}", ConfigProperties.getProperty("roomId"));

		clientHead = ConfigProperties.getProperty("clientHead")
			.replace("{replce}", Integer
			.toHexString(clientBody.getBytes().length + 16));
	}

	public void start() throws URISyntaxException, InterruptedException, UnsupportedEncodingException {
		// 建立连接
		WebSocket client = new WebSocket(url);
		client.connectBlocking();

		// 发送连接参数
        byte[] head = ByteUtils.hexToByteArray(clientHead);
        byte[] body = clientBody.getBytes(StandardCharsets.UTF_8);
        byte[] requestCode = ByteUtils.byteMerger(head, body);
        client.send(requestCode);

        // 定时发送心跳包
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				client.send(ByteUtils.hexToByteArray(heartByte));
				System.out.println("心跳发送成功");
			}
		}, 0L, 30000L);
	}
	
}
