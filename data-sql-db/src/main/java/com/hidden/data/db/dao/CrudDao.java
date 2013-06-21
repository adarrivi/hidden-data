package com.hidden.data.db.dao;

import java.util.List;

public interface CrudDao<T> {

	T findById(Integer id);

	T save(T item);

	List<T> loadAll();

}
