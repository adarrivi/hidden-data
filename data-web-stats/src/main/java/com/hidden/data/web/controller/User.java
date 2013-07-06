package com.hidden.data.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class User {
	@RequestMapping("/User")
	public String home() {
		return "User";
	}

	@RequestMapping("/redirectHome")
	public String redirectToHome() {
		return "Home";
	}

	/**
	 * Handles request for adding two numbers
	 */
	@RequestMapping(value = "addOperation", method = RequestMethod.POST)
	public @ResponseBody
	Integer add(
			@RequestParam(value = "inputNumber1", required = true) Integer inputNumber1,
			@RequestParam(value = "inputNumber2", required = true) Integer inputNumber2) {

		// Delegate to service to do the actual adding
		Integer sum = Integer.valueOf(inputNumber1.intValue()
				+ inputNumber2.intValue());
		// @ResponseBody will automatically convert the returned value into JSON
		// format
		// You must have Jackson in your classpath
		return sum;
	}
}
