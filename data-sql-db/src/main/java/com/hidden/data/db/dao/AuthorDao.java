package com.hidden.data.db.dao;

import com.hidden.data.db.model.Author;

public interface AuthorDao extends CrudDao<Author> {
	Author findByName(String name);
}
