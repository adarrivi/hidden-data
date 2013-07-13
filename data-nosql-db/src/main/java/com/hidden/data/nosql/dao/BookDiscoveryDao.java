package com.hidden.data.nosql.dao;

import java.util.List;

import com.hidden.data.nosql.model.discovery.BookDiscovery;

public interface BookDiscoveryDao extends CrudDao<BookDiscovery> {

	List<String> getDifferentBooks();

	List<String> getDifferentAuthors();

	List<String> getDifferentPatterns();

	List<BookDiscovery> findBookDiscoveries(String bookTitle, String pattern);
}
