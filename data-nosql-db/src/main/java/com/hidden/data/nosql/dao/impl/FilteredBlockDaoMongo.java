package com.hidden.data.nosql.dao.impl;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.model.FilteredBlock;

@Repository
public class FilteredBlockDaoMongo extends CrudDaoMongo<FilteredBlock>
		implements FilteredBlockDao {

	protected FilteredBlockDaoMongo() {
		super(FilteredBlock.class);
	}

	@Override
	public FilteredBlock findOneAndRemove() {
		return mongoOperations.findAndRemove(new Query(), FilteredBlock.class);
	}
}
