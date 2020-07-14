package com.saki9.danmaku.service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * @author 本間Saki
 */
public class MessageHandleService {

    public void messageHandle(ByteBuffer message) throws DataFormatException {
        String s = messageToJson(message);
        System.out.println(s);
    }

    public String messageToJson(ByteBuffer message) throws DataFormatException {
        byte[] messageBytes = message.array();
        byte[] mainMessageBytes = Arrays
            .copyOfRange(messageBytes, 16, messageBytes.length);

        if (messageBytes[16] != 120) {
            return new String(mainMessageBytes, StandardCharsets.UTF_8);
        }

        // 解压缩弹幕信息
        byte[] newByte = new byte[1024 * 5];
        Inflater inflater = new Inflater();
        inflater.setInput(mainMessageBytes);
        int inflate = inflater.inflate(newByte);
        newByte = Arrays.copyOfRange(newByte, 16, inflate);
        return new String(newByte, StandardCharsets.UTF_8);
    }

}
