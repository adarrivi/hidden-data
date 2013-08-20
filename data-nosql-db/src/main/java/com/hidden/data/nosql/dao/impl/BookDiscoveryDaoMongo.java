package com.hidden.data.nosql.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;

@Repository
public class BookDiscoveryDaoMongo extends CrudDaoMongo<BookDiscovery>
		implements BookDiscoveryDao {

	private static final String COLLECTION = "discovery.book";

	protected BookDiscoveryDaoMongo() {
		super(BookDiscovery.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDifferentBooks() {
		return mongoOperations.getCollection(COLLECTION).distinct("bookTitle");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDifferentAuthors() {
		return mongoOperations.getCollection(COLLECTION).distinct("author");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDifferentPatterns() {
		return mongoOperations.getCollection(COLLECTION).distinct(
				"pattern.name");
	}

	@Override
	public List<BookDiscovery> findBookDiscoveriesByBookAndPattern(String bookTitle,
			String patternName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("bookTitle").is(bookTitle)
				.and("pattern.name").is(patternName));
		return mongoOperations.find(query, BookDiscovery.class);
	}

	@Override
	public List<BookDiscovery> findPatternsPerBook(String bookTitle) {
		Query query = new Query();
		query.addCriteria(Criteria.where("bookTitle").is(bookTitle));
		return mongoOperations.find(query, BookDiscovery.class);
	}

}
