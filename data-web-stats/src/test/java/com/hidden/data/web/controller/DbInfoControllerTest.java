package com.hidden.data.web.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.web.dto.DatabaseInfoDto;
import com.hidden.data.web.service.StatisticsService;

public class DbInfoControllerTest {

	private static final DatabaseInfoDto DB_INFO = new DatabaseInfoDto(1, 2, 3);

	@Mock
	private StatisticsService statisticsService;

	@InjectMocks
	private DbInfoController victim = new DbInfoController();

	private String pageResult;
	private DatabaseInfoDto databaseInfo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void welcomePage_ReturnsWelcomePage() {
		whenWelcomePage();
		thenPageResultShouldBe("DbInfo");
	}

	private void whenWelcomePage() {
		pageResult = victim.welcomePage();
	}

	private void thenPageResultShouldBe(String expectedPage) {
		Assert.assertEquals(expectedPage, pageResult);
	}

	@Test
	public void getDatabaseInfo_ReturnsServiceDbInfo() {
		givenDbInfoFromService(DB_INFO);
		whenGetDatabaseInfo();
		thenDatabaseInfoShouldBe(DB_INFO);
	}

	private void givenDbInfoFromService(DatabaseInfoDto dbInfo) {
		Mockito.when(statisticsService.getDatabaseStats()).thenReturn(dbInfo);
	}

	private void whenGetDatabaseInfo() {
		databaseInfo = victim.getDatabaseInfo();
	}

	private void thenDatabaseInfoShouldBe(
			DatabaseInfoDto expectedDatabaseInfoDto) {
		Assert.assertEquals(expectedDatabaseInfoDto, databaseInfo);
	}

}
