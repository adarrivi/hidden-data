package com.hidden.data.db.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.DatabaseConnection;
import liquibase.database.jvm.HsqlConnection;
import liquibase.exception.LiquibaseException;
import liquibase.logging.LogFactory;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class InMemoryDaoTest {

	@Value("${driver}")
	private String driver;
	@Value("${url}")
	private String url;
	@Value("${username}")
	private String username;
	@Value("${password}")
	private String password;
	@Value("${dbchangelog}")
	private String dbchangelog;

	@Before
	public void setUp() {
		try {
			loadInMemoryDb();
		} catch (Exception ex) {
			throw new RuntimeException("Error during database initialization",
					ex);
		}
	}

	private void loadInMemoryDb() throws ClassNotFoundException, SQLException,
			LiquibaseException {
		loadDriver();
		DatabaseConnection hsqlConnection = getHsqlConnection();
		loadLiquibaseChangelog(hsqlConnection);
		hsqlConnection.close();
	}

	private void loadDriver() throws ClassNotFoundException {
		Class.forName(driver);
	}

	private DatabaseConnection getHsqlConnection() throws SQLException {
		Connection connection = getInMemoryConnection();
		return new HsqlConnection(connection);
	}

	private Connection getInMemoryConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	private void loadLiquibaseChangelog(DatabaseConnection hsqlConnection)
			throws LiquibaseException {
		ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();
		LogFactory.getLogger().setLogLevel("severe");
		Liquibase liquibase = new Liquibase(dbchangelog, resourceAccessor,
				hsqlConnection);
		liquibase.dropAll();
		liquibase.update(StringUtils.EMPTY);
	}

}
