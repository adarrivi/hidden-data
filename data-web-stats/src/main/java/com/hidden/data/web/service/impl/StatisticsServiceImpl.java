package com.hidden.data.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;
import com.hidden.data.web.dto.DatabaseInfoDto;
import com.hidden.data.web.dto.PatternsPerBookChart;
import com.hidden.data.web.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private BookDiscoveryDao bookDiscoveryDao;

	@Override
	public DatabaseInfoDto getDatabaseStats() {
		List<String> differentBooks = bookDiscoveryDao.getDifferentBooks();
		List<String> differentAuthors = bookDiscoveryDao.getDifferentAuthors();
		List<String> differentPatterns = bookDiscoveryDao
				.getDifferentPatterns();
		return new DatabaseInfoDto(differentBooks.size(),
				differentAuthors.size(), differentPatterns.size());
	}

	@Override
	public PatternsPerBookChart getPatternsPerBook() {
		PatternsPerBookChart chart = new PatternsPerBookChart();
		List<String> allPatternNames = bookDiscoveryDao.getDifferentPatterns();
		List<String> allBookTitles = bookDiscoveryDao.getDifferentBooks();
		chart.setBookTitles(allBookTitles);
		chart.setPatternNames(allPatternNames);
		for (String patternName : allPatternNames) {
			List<Integer> patternRepetitions = new ArrayList<Integer>();
			for (String bookTitle : allBookTitles) {
				List<BookDiscovery> bookDiscoveries = bookDiscoveryDao
						.findBookDiscoveries(bookTitle, patternName);
				patternRepetitions.add(bookDiscoveries.size());
			}
			chart.addPatternRepetitions(patternRepetitions);
		}
		return chart;
	}
}
