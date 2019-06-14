package com.saki9.barrageCatch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("barrageCatch")
@RestController
public class BarrageCatchController {
	
	@RequestMapping("/testCatch")
	public String TestCatch() {
		return "success";
	}

}
