package com.hidden.data.web.service;

import com.hidden.data.web.dto.DatabaseInfoDto;
import com.hidden.data.web.dto.PatternDistributionChart;
import com.hidden.data.web.dto.PatternsPerBookChart;

public interface StatisticsService {
	DatabaseInfoDto getDatabaseStats();

	PatternsPerBookChart getPatternsPerBook();

	PatternDistributionChart getPatternDistributionPerAllBooks();

}
