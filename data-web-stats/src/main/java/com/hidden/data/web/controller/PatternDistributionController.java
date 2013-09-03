package com.hidden.data.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hidden.data.web.dto.PatternDistributionChart;
import com.hidden.data.web.service.StatisticsService;

@Controller
public class PatternDistributionController {

	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping("/PatternDistribution")
	public String welcomePage() {
		return "PatternDistribution";
	}

	@RequestMapping(value = "getPatternDistributionChart", method = RequestMethod.GET)
	public @ResponseBody
	PatternDistributionChart getPatternsChart() {
		return statisticsService.getPatternDistributionPerAllBooks();
	}
}
