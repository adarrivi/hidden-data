package com.hidden.data.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;
import com.hidden.data.web.dto.DatabaseInfoDto;
import com.hidden.data.web.dto.PatternDistributionChart;
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
				differentPatterns.size(), differentAuthors.size());
	}

	@Override
	public PatternsPerBookChart getPatternsPerBook() {
		PatternsPerBookChart chart = new PatternsPerBookChart();
		List<String> allPatternNames = bookDiscoveryDao.getDifferentPatterns();
		List<String> allBookTitles = bookDiscoveryDao.getDifferentBooks();
		chart.setBookTitles(allBookTitles);
		chart.setPatternNames(allPatternNames);
		for (String patternName : allPatternNames) {
			List<Integer> patternRepetitions = getPatternRepetitionsInBooks(
					patternName, allBookTitles);
			chart.addPatternRepetitions(patternRepetitions);
		}
		return chart;
	}

	private List<Integer> getPatternRepetitionsInBooks(String patternName,
			List<String> bookTitles) {
		List<Integer> patternRepetitions = new ArrayList<Integer>();
		for (String bookTitle : bookTitles) {
			List<BookDiscovery> bookDiscoveries = bookDiscoveryDao
					.findBookDiscoveriesByBookAndPattern(bookTitle, patternName);
			patternRepetitions.add(bookDiscoveries.size());
		}
		return patternRepetitions;
	}

	@Override
	public PatternDistributionChart getPatternDistributionPerAllBooks() {
		PatternDistributionChart chart = new PatternDistributionChart();
		List<String> allBookTitles = bookDiscoveryDao.getDifferentBooks();
		for (String bookTitle : allBookTitles) {
			chart.addPatternsDistribution(bookTitle,
					getPatternDistributionPerBook(bookTitle));
		}
		return chart;
	}

	private List<Integer> getPatternDistributionPerBook(String bookTitle) {
		List<BookDiscovery> patternsPerBook = bookDiscoveryDao
				.findPatternsPerBook(bookTitle);
		PatternDistributionPerBook patternDistributionPerBook = new PatternDistributionPerBook(
				patternsPerBook.get(0).getBookTotalLines());
		patternDistributionPerBook.setPatternThreshold(patternsPerBook);
		return getAsPercentageList(patternDistributionPerBook
				.getPatternsPerPercentageThreshold());
	}

	private List<Integer> getAsPercentageList(List<Integer> valueList) {
		PercentageList list = new PercentageList(valueList);
		return list.getPercentaceList();
	}
}
