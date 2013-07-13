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

	@Override
	public List<BookDiscovery> findBookDiscoveries(String bookTitle,
			String patternName) {
		// BasicQuery query = new BasicQuery("{ 'bookTitle' : '" + bookTitle
		// + "', 'pattern.name' : '" + patternName + "' }");
		Query query = new Query();
		query.addCriteria(Criteria.where("bookTitle").is(bookTitle));
		System.out.println(query.toString());
		return mongoOperations.find(query, BookDiscovery.class);
	}

}
