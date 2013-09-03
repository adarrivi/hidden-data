package com.hidden.data.db.dao.impl;

import java.sql.Types;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;

//DBUnit doesn't recognise Types.BOOLEAN for hsqldb. This factory fixes the problem
class HsqlDataTypeFactory extends DefaultDataTypeFactory {

	@Override
	public DataType createDataType(int sqlType, String sqlTypeName)
			throws DataTypeException {
		if (sqlType == Types.BOOLEAN) {
			return DataType.BOOLEAN;
		}
		return super.createDataType(sqlType, sqlTypeName);
	}

}
