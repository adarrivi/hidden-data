package com.hidden.data.common.file.io;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.hidden.data.common.file.CommonsFileUtils;
import com.hidden.data.common.file.exception.FileException;

public class IOCommonsFileUtils implements CommonsFileUtils {

	private static final IOCommonsFileUtils INSTANCE = new IOCommonsFileUtils();

	public static IOCommonsFileUtils getInstance() {
		return INSTANCE;
	}

	private IOCommonsFileUtils() {
		// limiting scope
	}

	@Override
	public File getFileFromRelativePath(String relativePath) {
		URL resource = getResourceFromRelativePath(relativePath);
		return new File(resource.getPath());
	}

	private URL getResourceFromRelativePath(String relativePath) {
		URL resource = getClass().getResource(relativePath);
		if (resource == null) {
			throw new FileException("No file found in path:" + relativePath);
		}
		return resource;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<File> getFilesFromFolder(File folder) {
		assertIsAFolder(folder);
		return FileUtils.listFiles(folder, TrueFileFilter.TRUE, null);
	}

	private void assertIsAFolder(File folder) {
		if (!folder.isDirectory()) {
			throw new FileException("The given path is not a folder: "
					+ folder.getPath());
		}
	}

	@Override
	public Collection<File> getSubFolders(File folder) {
		assertIsAFolder(folder);
		File[] directories = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		return Arrays.asList(directories);
	}
}
