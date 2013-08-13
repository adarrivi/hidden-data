package com.hidden.data.common.property;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.file.exception.FileException;
import com.hidden.data.common.property.FileProperties;

public class FilePropertiesTest {

	private static final String NOT_EXISTING_RELATIVE_FILE_PATH = "notExistingFile";
	private static final String EXISTING_RELATIVE_FILE_PATH = "/file/Lorem_ipsum_dolor.txt";
	private static final String PROPERTY_KEY = "aProperty";
	private FileProperties victim;
	private String relativePath;

	@Mock
	private Properties properties;
	@Mock
	private ByteArrayInputStream mockedStream;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void newInstance_NotExistingPath_ThrowsFileEx() {
		expectFileEx();
		givenNotExistingPath();
		whenNewInstance();
	}

	private void expectFileEx() {
		expectedException.expect(FileException.class);
	}

	private void givenNotExistingPath() {
		relativePath = NOT_EXISTING_RELATIVE_FILE_PATH;
	}

	private void whenNewInstance() {
		victim = new FileProperties(getClass()
				.getResourceAsStream(relativePath), properties);
	}

	@Test
	public void newInstance_IOExWhenLoadingProperteis_ThrowsFileEx()
			throws IOException {
		expectFileEx();
		givenIOExWhenLoadingProperties();
		givenExistingFile();
		whenNewInstance();
	}

	private void givenIOExWhenLoadingProperties() throws IOException {
		Mockito.doThrow(new IOException()).when(properties)
				.load(Matchers.any(InputStream.class));
	}

	private void givenExistingFile() {
		relativePath = EXISTING_RELATIVE_FILE_PATH;
	}

	@Test
	public void getProperty_ExistingProperties_RetrievesFromProperties() {
		givenExistingProperties();
		whenGetProperty();
		thenShouldUseProperties();
	}

	private void givenExistingProperties() {
		givenExistingFile();
		whenNewInstance();
	}

	private void whenGetProperty() {
		victim.getProperty(PROPERTY_KEY);
	}

	private void thenShouldUseProperties() {
		Mockito.verify(properties).getProperty(PROPERTY_KEY);
	}

	@Test
	public void newInstance_ExistingProperty_Closes_Stream() throws IOException {
		whenNewMockedInstance();
		thenStreamShouldBeClosed();
	}

	private void whenNewMockedInstance() {
		victim = new FileProperties(mockedStream, properties);
	}

	private void thenStreamShouldBeClosed() throws IOException {
		Mockito.verify(mockedStream).close();
	}

}
