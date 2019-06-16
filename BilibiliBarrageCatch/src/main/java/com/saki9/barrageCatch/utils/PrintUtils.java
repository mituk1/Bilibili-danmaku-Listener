package com.saki9.barrageCatch.utils;

import java.nio.ByteBuffer;

public class PrintUtils {
	
	public static void ByteBufferToString(ByteBuffer message) {
		byte[] bytes = ByteUtils.decodeValue(message);
		String hexString = ByteUtils.bytesToHexString(bytes);
		String resultStr = ByteUtils.hexStringToString(stringToArrayStrByte(hexString));
		if (resultStr == null) {
			return;
		}
		String[] split = resultStr.split("---------");
		for (String string : split) {
			System.out.println(string);
		}
	}
	
	public static String stringToArrayStrByte(String hexString){
		StringBuffer sb = new StringBuffer();
		int endIndex = Integer.parseInt(hexString.substring(0, 8), 16) * 2;
		if(hexString.length() == endIndex){
			return hexString.substring(32, endIndex);
		}
		sb.append(hexString.substring(32, endIndex) + "2d2d2d2d2d2d2d2d2d" + stringToArrayStrByte(hexString.substring(endIndex, hexString.length())));
		return sb.toString();
	}
	
}
