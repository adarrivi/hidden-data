package com.hidden.data.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hidden.data.web.dto.DatabaseInfoDto;

@Controller
public class DatabaseInfoController {

	@RequestMapping(value = "getDatabaseInfo", method = RequestMethod.GET)
	public @ResponseBody
	DatabaseInfoDto getDatabaseInfo() {
		return new DatabaseInfoDto(3, 4, 5);
	}
}
