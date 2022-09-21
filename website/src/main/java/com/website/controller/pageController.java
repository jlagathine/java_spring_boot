package com.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class pageController {

	@GetMapping("/")
	public static String homePage() {
		return "index/Pages"; 
	}
}
