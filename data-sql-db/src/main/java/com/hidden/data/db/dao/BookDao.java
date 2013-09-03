package com.hidden.data.db.dao;

import java.util.Collection;

import com.hidden.data.db.model.Book;

public interface BookDao extends CrudDao<Book> {

	Book findByTitle(String title);

	Collection<String> getNotProcessedBookTitles();

}
