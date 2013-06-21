package com.hidden.data.nosql.dao.impl;

import org.springframework.stereotype.Repository;

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;

@Repository
public class BookDiscoveryDaoMongo extends CrudDaoMongo<BookDiscovery>
		implements BookDiscoveryDao {

	protected BookDiscoveryDaoMongo() {
		super(BookDiscovery.class);
	}

}
