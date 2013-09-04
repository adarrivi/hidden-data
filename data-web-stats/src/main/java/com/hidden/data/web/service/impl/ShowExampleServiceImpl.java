package com.hidden.data.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;
import com.hidden.data.web.dto.PatternExampleDto;
import com.hidden.data.web.service.ShowExampleService;

@Service
public class ShowExampleServiceImpl implements ShowExampleService {

	@Autowired
	private BookDiscoveryDao bookDiscoveryDao;

	@Override
	public PatternExampleDto getRandomExmaple() {
		BookDiscovery bookDiscovery = bookDiscoveryDao.findOneRandom();
		PatternExampleDto dto = new PatternExampleDto(
				bookDiscovery.getBookTitle(), bookDiscovery.getAuthor(),
				bookDiscovery.getPattern().getName(),
				bookDiscovery.getLinesContent());
		return dto;
	}

}
