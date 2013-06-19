package com.hidden.data.nosql.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.hidden.data.nosql.dao.CrudDao;

public class CrudDaoMongo<T> implements CrudDao<T> {

	@Autowired
	private MongoOperations mongoOperations;
	private Class<T> daoClass;

	protected CrudDaoMongo(Class<T> daoClass) {
		this.daoClass = daoClass;
	}

	@Override
	public T save(T objToSave) {
		mongoOperations.save(objToSave);
		return objToSave;
	}

	@Override
	public List<T> loadAll() {
		return mongoOperations.findAll(daoClass);
	}

}
