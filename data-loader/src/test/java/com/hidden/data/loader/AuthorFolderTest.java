package com.hidden.data.loader;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.common.file.RelativeFile;
import com.common.file.RelativeFileFactory;
import com.common.file.impl.RelativeFileFactoryImpl;
import com.hidden.data.loader.exception.LoaderException;

public class AuthorFolderTest extends
		RelativeFileIterableTestTemplate<RelativeFile> {

	private AuthorFolder victim;
	@Mock
	private RelativeFile mockedRelFile;
	@Mock
	private File mockedFile;
	private String authorName;
	private RelativeFileFactory relativeFileFactory = RelativeFileFactoryImpl
			.getInstance();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void getAuthorName_Empty_ThrowEx() {
		expectLoaderEx();
		givenAuthorName(StringUtils.EMPTY);
		whenGetAuthorName();
	}

	private void expectLoaderEx() {
		expectedException.expect(LoaderException.class);
	}

	private void givenAuthorName(String name) {
		Mockito.when(mockedRelFile.getFile()).thenReturn(mockedFile);
		Mockito.when(mockedFile.getName()).thenReturn(name);
		victim = new AuthorFolder(mockedRelFile, relativeFileFactory);
	}

	private void whenGetAuthorName() {
		authorName = victim.getAuthorName();
	}

	@Test
	public void getAuthorName_NoSeparation() {
		String name = "Anonymous";
		givenAuthorName(name);
		whenGetAuthorName();
		thenAuthorNameShouldBe(name);
	}

	private void thenAuthorNameShouldBe(String name) {
		Assert.assertEquals(name, authorName);
	}

	@Test
	public void getAuthorName_OneSeparation_ReplacesWithSpaces() {
		givenAuthorName("James.Joyce");
		whenGetAuthorName();
		thenAuthorNameShouldBe("James Joyce");
	}

	@Test
	public void getAuthorName_MultibleSeparation_ReplacesWithSpaces() {
		givenAuthorName("Jacob.Grimm.and.Wilhelm.Grimm");
		whenGetAuthorName();
		thenAuthorNameShouldBe("Jacob Grimm and Wilhelm Grimm");
	}

	@Override
	protected RelativeFileIterable<RelativeFile> getNewVictim() {
		return new AuthorFolder(folder, relativeFileFactory);
	}

}
