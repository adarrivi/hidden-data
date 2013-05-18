package com.common.file.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.common.TestObjectFactory;

public class RelativeBufferedReaderTest {

	private RelativeBufferedReader victim;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	@Mock
	private RelativeFileReader relativeFileReader;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws IOException {
		if (fileReader != null) {
			fileReader.close();
		}
	}

	@Test
	public void getBufferedReader_ReturnsCreatedFromRelativeFileReader()
			throws FileNotFoundException {
		givenRelativeFileReader();
		whenGetBufferedReader();
		thenGetBufferedReader();
	}

	private void givenRelativeFileReader() throws FileNotFoundException {
		fileReader = new FileReader(TestObjectFactory.getInstance()
				.getExistingFile());
		Mockito.when(relativeFileReader.getFileReader()).thenReturn(fileReader);
		victim = new RelativeBufferedReader(relativeFileReader);
	}

	private void whenGetBufferedReader() {
		bufferedReader = victim.getBufferedReader();
	}

	private void thenGetBufferedReader() {
		Assert.assertNotNull(bufferedReader);
		Mockito.verify(relativeFileReader).getFileReader();
	}

}
