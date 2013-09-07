package com.hidden.data.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hidden.data.web.dto.PatternExampleDto;
import com.hidden.data.web.service.ShowExampleService;

@Controller
public class ShowExampleController {

	@Autowired
	private ShowExampleService showExampleService;

	@RequestMapping(value = { "/ShowExample" })
	public String welcomePage() {
		return "ShowExample";
	}

	@RequestMapping(value = "getRandomExample", method = RequestMethod.GET)
	public @ResponseBody
	PatternExampleDto getRandomExample() {
		return showExampleService.getRandomExmaple();
	}
}
