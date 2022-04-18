package com.ekankhek.ekankhek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/login.html")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
