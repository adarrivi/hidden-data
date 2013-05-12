package com.hidden.data.producer.book.text;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.hidden.data.producer.TestObjectCreator;
import com.hidden.data.producer.exception.ProducerException;

public class TextFileTest {

	private TextFile victim;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void openFolderThrowsEx() {
		expectedException.expect(ProducerException.class);
		victim = TestObjectCreator.getInstance().createInvalidFolderTextFile();
		victim.openFile();
	}

	@Test
	public void readLineWithClosedFileThrowsEx() {
		expectedException.expect(ProducerException.class);
		victim = TestObjectCreator.getInstance().createTextFile();
		victim.readLine();
	}

	@Test
	public void readLineReturnsFirstLine() {
		victim = TestObjectCreator.getInstance().createTextFile();
		victim.openFile();
		String firstLine = victim.readLine();
		Assert.assertEquals(TestObjectCreator.BOOK_CONTENT[0], firstLine);
	}

	@Test
	public void repeatReadLineReturnsBookContent() {
		victim = TestObjectCreator.getInstance().createTextFile();
		victim.openFile();
		assertBookContent(TestObjectCreator.BOOK_CONTENT);

	}

	private void assertBookContent(String[] bookContent) {
		int i = 0;
		String line = victim.readLine();
		while (line != null) {
			Assert.assertEquals(bookContent[i++], line);
			line = victim.readLine();
		}
		Assert.assertEquals(bookContent.length, i);
	}

	@Test
	public void emptyBookContainsNoLines() {
		victim = TestObjectCreator.getInstance().createEmptyTextFile();
		victim.openFile();
		String bookContent[] = new String[0];
		assertBookContent(bookContent);
	}

	@Test
	public void openReadCloseAlwaysReturnsFirstLine() {
		victim = TestObjectCreator.getInstance().createTextFile();
		victim.openFile();
		String firstLine = victim.readLine();
		victim.closeFile();
		victim.openFile();
		Assert.assertEquals(firstLine, victim.readLine());
	}

}
