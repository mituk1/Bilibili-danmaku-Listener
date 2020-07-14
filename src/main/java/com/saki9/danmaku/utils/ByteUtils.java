package com.saki9.danmaku.utils;

/**
 * @author 本間Saki
 */
public class ByteUtils {

	/** 
	 * Hex字符串转 byte
	 * @param inHex 待转换的 Hex字符串
	 * @return  转换后的 byte
	 */  
	public static byte hexToByte(String inHex) {
	   return (byte)Integer.parseInt(inHex,16);  
	} 
	    
    /** 
     * hex 字符串转 byte 数组
     * @param inHex 待转换的 Hex 字符串
     * @return  转换后的 byte 数组结果
     */  
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();  
        byte[] result;  
        if (hexlen % 2 == 1) {
            //奇数  
            hexlen++;  
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        }else {  
            //偶数  
            result = new byte[(hexlen / 2)];
        }  
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;  
        }
        return result;   
    }
    
    /**
     * 将两个byte数组拼接为单独对象
     * @param byte_1
     * @param byte_2
     * @return
     */
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){
    	byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
    	System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
    	return byte_3;
    }
    
    /**
     * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
    StringBuilder stringBuilder = new StringBuilder("");
    if (src == null || src.length <= 0) {
    return null;
    }
    for (int i = 0; i < src.length; i++) {
    int v = src[i] & 0xFF;
    String hv = Integer.toHexString(v);
    if (hv.length() < 2) {
    stringBuilder.append(0);
    }
    stringBuilder.append(hv);
    }
    return stringBuilder.toString();
    }
    
}
