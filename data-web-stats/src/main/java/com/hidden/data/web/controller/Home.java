package com.hidden.data.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
	@RequestMapping("/Home")
	public String home() {
		return "Home";
	}

	@RequestMapping("/redirectToUser")
	public String redirectToUser() {
		return "User";
	}
}
