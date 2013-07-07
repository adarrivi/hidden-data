package com.hidden.data.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hidden.data.web.dto.PatternDto;

@Controller
public class PatternsPerBookController {

	private List<PatternDto> allPatterns = new ArrayList<PatternDto>();

	@RequestMapping("/PatternsPerBook")
	public String welcomePage() {
		return "PatternsPerBook";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public @ResponseBody
	List<PatternDto> list() {
		return allPatterns;
	}

	@RequestMapping(value = "addPattern", method = RequestMethod.POST)
	public void addPattern(HttpServletResponse response) {
		response.getBufferSize();
		PatternDto dto = new PatternDto("pattern" + allPatterns.size());
		allPatterns.add(dto);
	}
}
