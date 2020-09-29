package com.saki9.danmaku.service;

import com.saki9.danmaku.conf.ConfigProperties;
import com.saki9.danmaku.conf.WebSocket;

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
        byte[] head = hexToByteArray(clientHead);
        byte[] body = clientBody.getBytes(StandardCharsets.UTF_8);
        byte[] requestCode = byteMerger(head, body);
        client.send(requestCode);

        // 定时发送心跳包
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				client.send(hexToByteArray(heartByte));
				System.out.println("心跳发送成功");
			}
		}, 0L, 30000L);
	}

	/**
	 * @param hexStr Hex 字符串
	 * @return byte[]
	 */
	private byte[] hexToByteArray(String hexStr) {
		if (hexStr.length() % 2 == 1) {
			hexStr = "0" + hexStr;
		}

		int hexlen = hexStr.length();
		byte[] result = new byte[(hexlen / 2)];

		for (int i = 0, j = 0; i < hexlen; i += 2, j++) {
			result[j] = (byte)Integer.parseInt(hexStr.substring(i, i + 2),16);
		}
		return result;
	}

	/**
	 * @param byteL left
	 * @param byteR right
	 * @return left + right
	 */
	private byte[] byteMerger(byte[] byteL, byte[] byteR){
		byte[] byteArr = new byte[byteL.length + byteR.length];
		System.arraycopy(byteL, 0, byteArr, 0, byteL.length);
		System.arraycopy(byteR, 0, byteArr, byteL.length, byteR.length);
		return byteArr;
	}
	
}
