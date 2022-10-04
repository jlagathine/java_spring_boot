package com.choisy.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateControlleur {
	

	@GetMapping("login")
	public String getLoginview() {
		return "login";
	}
	
	@GetMapping("user")
	public String getUserview() {
		return "user";
	}
}
