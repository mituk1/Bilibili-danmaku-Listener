package com.saki9.barrageCatch.run;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.saki9.barrageCatch.service.MakeClientService;

public class Run {

	public static void main(String[] args) throws UnsupportedEncodingException, URISyntaxException, InterruptedException {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		MakeClientService makeClientService = ac.getBean("makeClientService", MakeClientService.class);
		makeClientService.StartService();
	}

}
