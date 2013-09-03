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
import org.apache.log4j.Logger;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hidden.data.common.file.io.IOCommonsFileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationDbContext-test.xml" })
public abstract class InMemoryDaoIntegrationTest {

	private static final Logger LOG = Logger
			.getLogger(InMemoryDaoIntegrationTest.class);

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
			LOG.error(ex);
			Assert.fail("Error during database initialization:"
					+ ex.getMessage());
		}
	}

	private void loadInMemoryDb() throws ClassNotFoundException, SQLException,
			LiquibaseException, DatabaseUnitException {
		loadDriver();
		loadLiquibase();
		loadDBUnit();
	}

	private void loadLiquibase() throws LiquibaseException, SQLException {
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

	private void loadDBUnit() throws SQLException, DatabaseUnitException {
		IDatabaseConnection connection = getDBUnitConnection();
		setHsqlExtraDataTypes(connection);
		IDataSet dataSet = new CsvDataSet(IOCommonsFileUtils.getInstance()
				.getFileFromRelativePath("/database/dataset"));
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}

	private void setHsqlExtraDataTypes(IDatabaseConnection connection) {
		DatabaseConfig configuration = connection.getConfig();
		configuration.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new HsqlDataTypeFactory());

	}

	private IDatabaseConnection getDBUnitConnection() throws SQLException {
		Connection connection = getInMemoryConnection();
		return new org.dbunit.database.DatabaseConnection(connection);
	}

}
