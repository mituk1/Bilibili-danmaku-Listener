package com.saki9.danmaku.run;

import com.saki9.danmaku.service.MakeClientService;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author 本間Saki
 */
public class Run {
	
	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		new MakeClientService().start();
	}

}
