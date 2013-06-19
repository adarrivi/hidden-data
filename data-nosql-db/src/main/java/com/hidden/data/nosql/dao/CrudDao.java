package com.hidden.data.nosql.dao;

import java.util.List;

public interface CrudDao<T> {

	T save(T objToSave);

	List<T> loadAll();

}
