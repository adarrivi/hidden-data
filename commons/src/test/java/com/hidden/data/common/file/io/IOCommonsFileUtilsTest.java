package com.hidden.data.common.file.io;

import java.io.File;
import java.net.URL;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.hidden.data.common.file.CommonsFileUtils;
import com.hidden.data.common.file.exception.FileException;

public class IOCommonsFileUtilsTest {

	private static final String FILES_FOLDER = "/file/";
	private static final String EXISTING_LARGE_FILE = FILES_FOLDER
			+ "largeFile.txt";
	private static final String EXISTING_SHORT_FILE = FILES_FOLDER
			+ "shortFile.txt";
	private static final String EMPTY_FILE = FILES_FOLDER + "emptyFile.txt";
	private static final String NOT_EXISTING_FILE_NAME = "doesNotExist.txt";
	private static final String ROOT_RESOURCES_FOLDER = "/";

	private CommonsFileUtils victim = IOCommonsFileUtils.getInstance();
	private File file;
	private String filePath;
	private Collection<File> files;
	private String fileContent;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void getFileFromRelativePath_notExistingFile_TrhowsFileEx() {
		expectFileException();
		givenNotExistingFile();
		whenGetFileFromRelativePath();
	}

	private void expectFileException() {
		expectedException.expect(FileException.class);
	}

	private void givenNotExistingFile() {
		filePath = NOT_EXISTING_FILE_NAME;
	}

	private void whenGetFileFromRelativePath() {
		file = victim.getFileFromRelativePath(filePath);
	}

	@Test
	public void getfileFromRelativePath_ExistingFile_ReturnsFile() {
		givenExistingFilePath();
		whenGetFileFromRelativePath();
		thenShouldReturnExistingFile();
	}

	private void givenExistingFilePath() {
		filePath = EXISTING_LARGE_FILE;
	}

	private void thenShouldReturnExistingFile() {
		Assert.assertNotNull(file);
		Assert.assertTrue(file.exists());
	}

	@Test
	public void getFilesFromFolder_NotAFolder_ThrowsFileEx() {
		expectFileException();
		givenExistingFile();
		whenGetFilesFromFolder();
	}

	private void givenExistingFile() {
		file = getFile(EXISTING_LARGE_FILE);
		givenExistingFilePath();
	}

	private File getFile(String path) {
		URL resource = getClass().getResource(path);
		return new File(resource.getPath());
	}

	private void whenGetFilesFromFolder() {
		files = victim.getFilesFromFolder(file);
	}

	@Test
	public void getFilesFromFolder_EmptyFolder_ReturnsEmptyCollection() {
		givenEmptyFolder();
		whenGetFilesFromFolder();
		thenFilesInFolderShouldBeEmpty();
	}

	private void givenEmptyFolder() {
		// points to scr/test/resources is empty (as per having files, not sub
		// folders)
		file = getFile(ROOT_RESOURCES_FOLDER);
	}

	private void thenFilesInFolderShouldBeEmpty() {
		Assert.assertTrue(files.isEmpty());
	}

	@Test
	public void getFilesFromFolder_NotEmptyFolder_ReturnsNotEmpty() {
		givenNotEmptyFolder();
		whenGetFilesFromFolder();
		thenFilesInFolderShouldNotBeEmpty();
	}

	private void givenNotEmptyFolder() {
		file = getFile(FILES_FOLDER);
	}

	private void thenFilesInFolderShouldNotBeEmpty() {
		Assert.assertFalse(files.isEmpty());
	}

	@Test
	public void getSubFolders_NotAFolder_ThrowsEx() {
		expectFileException();
		givenExistingFile();
		whenGetSubFolders();
	}

	private void whenGetSubFolders() {
		files = victim.getSubFolders(file);
	}

	@Test
	public void getSubFolders_NoSubfolders_ReturnsEmpty() {
		givenFolderWithNoSubfolders();
		whenGetSubFolders();
		thenFilesInFolderShouldBeEmpty();
	}

	private void givenFolderWithNoSubfolders() {
		file = getFile(FILES_FOLDER);
	}

	@Test
	public void getSubFolders_Subfolders_ReturnsSubfolders() {
		givenFolderWithSubfolder();
		whenGetSubFolders();
		thenFilesInFolderShouldNotBeEmpty();
	}

	private void givenFolderWithSubfolder() {
		file = getFile(ROOT_RESOURCES_FOLDER);
	}

	@Test
	public void getFileFromAbsolutePath_ReturnsNewFile() {
		whenGetFileFromAbsolutePath();
		thenShouldReturnExistingFile();
	}

	private void whenGetFileFromAbsolutePath() {
		File existingFile = victim.getFileFromRelativePath(EXISTING_LARGE_FILE);
		file = victim.getFileFromAbsolutePath(existingFile.getAbsolutePath());
	}

	@Test
	public void getFileContentAsString_Folder_ThrowsFileEx() {
		expectFileException();
		givenEmptyFolder();
		whenGetFileContentAsString();
	}

	private void whenGetFileContentAsString() {
		fileContent = victim.getFileContentAsString(file);
	}

	@Test
	public void getFileContentAsString_EmptyFile_ReturnsEmptyString() {
		givenEmptyFile();
		whenGetFileContentAsString();
		thenFileContentShouldBe(StringUtils.EMPTY);
	}

	private void givenEmptyFile() {
		file = getFile(EMPTY_FILE);
	}

	private void thenFileContentShouldBe(String expectedContent) {
		Assert.assertEquals(expectedContent, fileContent);
	}

	@Test
	public void getFileContentAsString_ReturnsFileContent() {
		givenShortFile();
		whenGetFileContentAsString();
		StringBuffer expectedContent = new StringBuffer();
		expectedContent
				.append("Lorem ipsum dolor sit amet, consectetur adipisicing elit,")
				.append(System.getProperty("line.separator"))
				.append("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
		thenFileContentShouldBe(expectedContent.toString());
	}

	private void givenShortFile() {
		file = getFile(EXISTING_SHORT_FILE);
	}
}
