package com.hidden.data.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hidden.data.web.dto.PatternDto;
import com.hidden.data.web.dto.PatternsInBook;

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

	@RequestMapping(value = "getPatternsChart", method = RequestMethod.GET)
	public @ResponseBody
	List<PatternsInBook> getPatternsChart() {
		Random randomizer = new Random();
		randomizer.setSeed(System.currentTimeMillis());
		// int numberOfBooks = randomizer.nextInt(9) + 1;
		// int numberOfPatterns = randomizer.nextInt(4) + 1;
		int numberOfBooks = 3;
		int numberOfPatterns = 4;

		List<PatternsInBook> allBooks = new ArrayList<PatternsInBook>();
		for (int i = 0; i < numberOfBooks; i++) {
			PatternsInBook patternsInBook = new PatternsInBook();
			for (int j = 0; j < numberOfPatterns; j++) {
				patternsInBook.addNumberOfPatterns(randomizer.nextInt(9) + 1);
			}
			allBooks.add(patternsInBook);
		}
		return allBooks;
	}
}
