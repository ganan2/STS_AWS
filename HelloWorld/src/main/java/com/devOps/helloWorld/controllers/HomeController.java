package com.devOps.helloWorld.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** @Controller is used to indicate that this is a Spring MVC Controller */
@Controller
public class HomeController {

	/**	@RequestMapping("/") is used to invoke index.html when there is an http request. */
	@RequestMapping("/")
	public String sayHello() {
		return "index";	/** 'index' is called the view-name. */
	}
}
