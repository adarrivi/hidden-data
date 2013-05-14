package com.hidden.data.producer.file;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.hidden.data.producer.TestObjectFactory;
import com.hidden.data.producer.exception.ProducerException;

public class BufferFileReaderTest {

	private FileBufferReader victim;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void getReaderWithFolderThrowsEx() {
		expectedException.expect(ProducerException.class);
		victim = new BufferFileReader(TestObjectFactory.getInstance()
				.getFolderFile());
	}

	@Test
	public void readLine() {
		victim = new BufferFileReader(TestObjectFactory.getInstance()
				.getExistingFile());
		Assert.assertFalse(StringUtils.isEmpty(victim.readLine()));
		victim.close();
	}
}
