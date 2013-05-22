package com.hidden.data.db.dao;

import java.util.List;

public interface CrudDao<T> {

	T save(T item);

	List<T> loadAll();

	void delete(T item);

}
