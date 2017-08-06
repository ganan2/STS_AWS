package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayloadController {
	
	public static String PAYLOAD_VIEW_NAME = "payload/payload";
	
	@RequestMapping("/payload")
	public String payload() {
		return PAYLOAD_VIEW_NAME;
	}
}
