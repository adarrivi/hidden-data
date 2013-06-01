package com.hidden.data.producer.book;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.common.file.reader.FileLineIterator;
import com.hidden.data.producer.util.TestObjectFactory;

public class SpaceBookTest {

	private static final String COMPLEX_LINE = " a bc  de   ";
	private static final boolean[] COMPLEX_LINE_TRANSFORMED = { true, false,
			true, false, false, true, true, false, false, true, true, true };
	private static final String[] MULTIPLE_LINES_CONTENT = { COMPLEX_LINE,
			COMPLEX_LINE, COMPLEX_LINE, COMPLEX_LINE };
	private Transformable<BitSet> victim;
	private Iterator<Line<String>> bookIterator;
	private List<Line<BitSet>> transformedBook;

	@Test
	public void transform_EmptyFile_ReturnsEmpty() {
		givenEmptyFile();
		whenTransform();
		thenTransformedBookShouldBeEmpty();
	}

	private void givenEmptyFile() {
		bookIterator = BookFactory.getInstance().createTextBookIterator(
				TestObjectFactory.BOOK_ID,
				TestObjectFactory.getInstance()
						.createEmptyStringArrayIterator());
		createVictim();
	}

	private void createVictim() {
		victim = new SpaceBook(TestObjectFactory.BOOK_ID,
				TestObjectFactory.BOOK_TITLE, bookIterator);
	}

	private void whenTransform() {
		transformedBook = victim.transform();
	}

	private void thenTransformedBookShouldBeEmpty() {
		Assert.assertNotNull(transformedBook);
		Assert.assertTrue(transformedBook.isEmpty());
	}

	@Test
	public void transform_OneLineEmpty_ReturnsLineEmpty() {
		givenOneLineFile(StringUtils.EMPTY);
		whenTransform();
		thenExpectOneLineAs(new boolean[0]);
	}

	private void givenOneLineFile(String line) {
		String content[] = new String[1];
		content[0] = line;
		setCustomIterator(content);
		createVictim();
	}

	private void setCustomIterator(String[] content) {
		FileLineIterator<String> customLineIterator = TestObjectFactory
				.getInstance().createCustomArrayIterator(content);
		bookIterator = BookFactory.getInstance().createTextBookIterator(
				TestObjectFactory.BOOK_ID, customLineIterator);
	}

	private BitSet createBitSet(boolean... values) {
		BitSet bitSet = new BitSet(values.length);
		for (int i = 0; i < values.length; i++) {
			if (values[i]) {
				bitSet.set(i);
			}
		}
		return bitSet;
	}

	private void thenExpectOneLineAs(boolean... values) {
		Assert.assertNotNull(transformedBook);
		Assert.assertEquals(1, transformedBook.size());
		BitSet expectedResult = createBitSet(values);
		Assert.assertTrue(expectedResult.equals(transformedBook.get(0)
				.getRowContent()));
	}

	@Test
	public void transform_OneLineNoSpaces_ReturnsLineEmpty() {
		givenOneLineFile("abcde");
		whenTransform();
		thenExpectOneLineAs(new boolean[0]);
	}

	@Test
	public void transform_OneLineSpaceBeginning_ReturnsSpaceBeginning() {
		givenOneLineFile(" abcde");
		whenTransform();
		thenExpectOneLineAs(true);
	}

	@Test
	public void transform_OneLineTwoSpaceBeginning_ReturnsTwoSpaceBeginning() {
		givenOneLineFile("  abcde");
		whenTransform();
		thenExpectOneLineAs(true, true);
	}

	@Test
	public void transform_OneLineSpaceMiddle_ReturnsSpaceMiddle() {
		givenOneLineFile("ab cde");
		whenTransform();
		thenExpectOneLineAs(false, false, true);
	}

	@Test
	public void transform_OneLineTwoSpaceMiddle_ReturnsTwoSpaceMiddle() {
		givenOneLineFile("ab  cde");
		whenTransform();
		thenExpectOneLineAs(false, false, true, true);
	}

	@Test
	public void transform_OneLineSpaceEnd_ReturnsSpaceEnd() {
		givenOneLineFile("abcde ");
		whenTransform();
		thenExpectOneLineAs(false, false, false, false, false, true);
	}

	@Test
	public void transform_OneLineTwoSpaceEnd_ReturnsTwoSpaceEnd() {
		givenOneLineFile("abcde  ");
		whenTransform();
		thenExpectOneLineAs(false, false, false, false, false, true, true);
	}

	@Test
	public void transform_OneLineComplex_ReturnsOneLineComplex() {
		givenOneLineFile(COMPLEX_LINE);
		whenTransform();
		thenExpectOneLineAs(true, false, true, false, false, true, true, false,
				false, true, true, true);
	}

	@Test
	public void transform_MultipleLines_TransformsAll() {
		givenMultipleLines();
		whenTransform();
		thenExpectAllLinesTransformed();
	}

	private void givenMultipleLines() {
		setCustomIterator(MULTIPLE_LINES_CONTENT);
		createVictim();
	}

	private void thenExpectAllLinesTransformed() {
		Assert.assertNotNull(transformedBook);
		Assert.assertEquals(MULTIPLE_LINES_CONTENT.length,
				transformedBook.size());
		BitSet expectedResult = createBitSet(COMPLEX_LINE_TRANSFORMED);
		for (Line<BitSet> line : transformedBook) {
			Assert.assertTrue(expectedResult.equals(line.getRowContent()));
		}
	}
}
