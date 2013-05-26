package com.hidden.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.jvm.HsqlConnection;
import liquibase.logging.LogFactory;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;

public class HsqlDatabase {
	private static final String CHANGE_LOG = "src/main/resources/liquibase/db.changelog.xml";
	private static final String CONNECTION_STRING = "jdbc:hsqldb:mem:datasample;shutdown=false";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";

	private Connection holdingConnection;
	private Liquibase liquibase;

	public void setUp(String contexts) {
		try {
			ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();
			Class.forName("org.hsqldb.jdbcDriver");

			holdingConnection = getConnectionImpl();
			HsqlConnection hsconn = new HsqlConnection(holdingConnection);
			LogFactory.getLogger().setLogLevel("severe");
			liquibase = new Liquibase(CHANGE_LOG, resourceAccessor, hsconn);
			liquibase.dropAll();
			liquibase.update(contexts);
			hsconn.close();
		} catch (Exception ex) {
			throw new RuntimeException("Error during database initialization",
					ex);
		}
	}

	private Connection getConnectionImpl() throws SQLException {
		return DriverManager.getConnection(CONNECTION_STRING, USER_NAME,
				PASSWORD);
	}
}
