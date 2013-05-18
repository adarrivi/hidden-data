package com.hidden.data.producer.book;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.common.file.FileLineIterator;
import com.hidden.data.producer.util.TestObjectFactory;

public class TextBookIteratorTest {

	private TextBookIterator victim;
	private FileLineIterator<String> fileContentIterator;
	private Line<String> resultLine;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void readNextLine_EmptyFile_ReturnsEmptyLine() {
		givenEmptyFile();
		whenReadNextLine();
		thenLineShouldBeEmpty();
	}

	private void givenEmptyFile() {
		fileContentIterator = TestObjectFactory.getInstance()
				.createEmptyStringArrayIterator();
		createVictim();
	}

	private void createVictim() {
		victim = new TextBookIterator(TestObjectFactory.BOOK_ID,
				fileContentIterator);
	}

	private void whenReadNextLine() {
		resultLine = victim.next();
	}

	private void thenLineShouldBeEmpty() {
		Assert.assertNotNull(resultLine);
		Assert.assertTrue(resultLine.isEmpty());
	}

	@Test
	public void readNextLine_TwiceReadEmptyFile_ReturnsEmptyLine() {
		givenEmptyFile();
		whenReadNextLine();
		thenLineShouldBeEmpty();
		whenReadNextLine();
		thenLineShouldBeEmpty();
	}

	@Test
	public void readNextLine_NotEmptyFile_ReturnsNotEmpty() {
		givenNotEmptyFile();
		whenReadNextLine();
		thenLineShouldNotBeEmpty();
	}

	private void givenNotEmptyFile() {
		fileContentIterator = TestObjectFactory.getInstance()
				.createStringArrayIterator();
		createVictim();
	}

	private void thenLineShouldNotBeEmpty() {
		Assert.assertNotNull(resultLine);
		Assert.assertFalse(resultLine.isEmpty());
	}

	@Test
	public void readNextLine_TwiceReadNotEmptyFile_ReturnsNotEmpty() {
		givenNotEmptyFile();
		whenReadNextLine();
		thenLineShouldNotBeEmpty();
		whenReadNextLine();
		thenLineShouldNotBeEmpty();
	}

	@Test
	public void readNextLine_ReadWholeContent_ReturnsExpectedLines() {
		givenNotEmptyFile();
		for (int i = 0; i < TestObjectFactory.BOOK_CONTENT.length; i++) {
			whenReadNextLine();
			thenLineShouldBe(i, TestObjectFactory.BOOK_CONTENT[i]);
		}
		whenReadNextLine();
		thenLineShouldBeEmpty();
	}

	private void thenLineShouldBe(int row, String lineContent) {
		Assert.assertEquals(TestObjectFactory.BOOK_ID, resultLine.getBookId());
		Assert.assertEquals(row, resultLine.getRow());
		Assert.assertEquals(lineContent, resultLine.getRowContent());
	}

	@Test
	public void remove_ThrowsUnsupportedOp() {
		expectUnsupportedOpEx();
		whenRemove();
	}

	private void expectUnsupportedOpEx() {
		expectedException.expect(UnsupportedOperationException.class);
		createVictim();
	}

	private void whenRemove() {
		victim.remove();
	}
}
