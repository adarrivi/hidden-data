package com.hidden.data.nosql.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;

@Repository
public class BookDiscoveryDaoMongo extends CrudDaoMongo<BookDiscovery>
		implements BookDiscoveryDao {

	protected BookDiscoveryDaoMongo() {
		super(BookDiscovery.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDifferentBooks() {
		return mongoOperations.getCollection("discovery.book").distinct(
				"bookTitle");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDifferentAuthors() {
		return mongoOperations.getCollection("discovery.book").distinct(
				"author");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDifferentPatterns() {
		return mongoOperations.getCollection("discovery.book").distinct(
				"pattern.name");
	}

}
