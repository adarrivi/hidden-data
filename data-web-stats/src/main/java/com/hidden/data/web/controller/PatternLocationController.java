package com.hidden.data.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hidden.data.web.dto.PatternsPerBookChart;
import com.hidden.data.web.service.StatisticsService;

@Controller
public class PatternLocationController {

	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping("/PatternLocation")
	public String welcomePage() {
		return "PatternLocation";
	}

	@RequestMapping(value = "getPatternLocationChart", method = RequestMethod.GET)
	public @ResponseBody
	PatternsPerBookChart getPatternsChart() {
		return statisticsService.getPatternsPerBook();
	}
}
