package com.hidden.data.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hidden.data.web.dto.DatabaseInfoDto;
import com.hidden.data.web.service.StatisticsService;

@Controller
public class DatabaseInfoController {

	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping(value = "getDatabaseInfo", method = RequestMethod.GET)
	public @ResponseBody
	DatabaseInfoDto getDatabaseInfo() {
		return statisticsService.getDatabaseStats();
	}
}
