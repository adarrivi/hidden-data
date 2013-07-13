package com.hidden.data.web.service;

import java.util.List;

import com.hidden.data.web.dto.DatabaseInfoDto;
import com.hidden.data.web.dto.PatternsInBook;

public interface StatisticsService {
	DatabaseInfoDto getDatabaseStats();

	List<PatternsInBook> getPatternsPerBook();
}
