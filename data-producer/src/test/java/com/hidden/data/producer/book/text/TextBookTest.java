package com.hidden.data.producer.book.text;

import java.util.BitSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.producer.TestObjectFactory;
import com.hidden.data.producer.book.Line;

public class TextBookTest {

	private static final String BOOK_LINE = "Lorem ipsum dolor sit amet, consectetur adipisicing elit,";
	private static final int MAX_LINES_IN_CONENT = TestObjectFactory.BOOK_CONTENT.length;
	private TextBook victim;
	private List<Line<BitSet>> transformedBook;

	@Mock
	private TextFile mockedFile;
	@InjectMocks
	private TextBook mockedVictim = new TextBook(TestObjectFactory.BOOK_ID,
			TestObjectFactory.BOOK_TITLE, mockedFile);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		victim = TestObjectFactory.getInstance().createTextBook();
	}

	@Test
	public void readNoLinesReturnsEmpty() {
		List<Line<String>> lines = victim.readNextLines(0);
		Assert.assertTrue(lines.isEmpty());
	}

	@Test
	public void readNegativeLinesReadsWholeFile() {
		List<Line<String>> lines = victim.readNextLines(-1);
		Assert.assertEquals(MAX_LINES_IN_CONENT, lines.size());
	}

	@Test
	public void readOnlyOneLine() {
		int linesToRead = 1;
		List<Line<String>> lines = victim.readNextLines(linesToRead);
		Assert.assertEquals(linesToRead, lines.size());
	}

	@Test
	public void readLessLinesThanTheFullContent() {
		int linesToRead = MAX_LINES_IN_CONENT - 3;
		List<Line<String>> lines = victim.readNextLines(linesToRead);
		Assert.assertEquals(linesToRead, lines.size());
	}

	@Test
	public void readFullContent() {
		int linesToRead = MAX_LINES_IN_CONENT;
		List<Line<String>> lines = victim.readNextLines(linesToRead);
		Assert.assertEquals(MAX_LINES_IN_CONENT, lines.size());
	}

	@Test
	public void readMoreLinesReturnsMaximunContent() {
		int linesToRead = MAX_LINES_IN_CONENT * 2;
		List<Line<String>> lines = victim.readNextLines(linesToRead);
		Assert.assertEquals(MAX_LINES_IN_CONENT, lines.size());
	}

	@Test
	public void transformEmptyFileReturnsEmptyList() {
		Mockito.when(mockedFile.readLine()).thenReturn(null);
		transformedBook = mockedVictim.transformBook();
		Assert.assertNotNull(transformedBook);
		Assert.assertTrue(transformedBook.isEmpty());
		Mockito.verify(mockedFile).readLine();
	}

	@Test
	public void transformEmptyLineReturnsEmptyLine() {
		transformLine(StringUtils.EMPTY);
		verifyOneLineSetUp(new BitSet(0));
	}

	private void transformLine(String line) {
		Mockito.when(mockedFile.readLine()).thenReturn(line, (String) null);
		transformedBook = mockedVictim.transformBook();
	}

	private void verifyOneLineSetUp(BitSet expectedResult) {
		Assert.assertNotNull(transformedBook);
		Assert.assertEquals(1, transformedBook.size());
		Line<BitSet> line = transformedBook.get(0);
		Assert.assertEquals(expectedResult, line.getLineContent());
		Assert.assertEquals(0, line.getRow());
		Assert.assertEquals(mockedVictim.getId(), line.getBookId());
		Mockito.verify(mockedFile, Mockito.atLeast(2)).readLine();
	}

	@Test
	public void transformNoSpacesReturnsAllFalse() {
		String line = "nospaces";
		BitSet expectedResult = new BitSet(line.length());
		transformLine(line);
		verifyOneLineSetUp(expectedResult);
	}

	@Test
	public void transformSpaceBeginingRetrunsBitBegining() {
		String line = " spaceBegining";
		BitSet expectedResult = new BitSet(line.length());
		expectedResult.set(0);
		transformLine(line);
		verifyOneLineSetUp(expectedResult);
	}

	@Test
	public void transformSpaceEndRetrunsBitEnd() {
		String line = "spaceEnd ";
		BitSet expectedResult = new BitSet(line.length());
		expectedResult.set(line.length() - 1);
		transformLine(line);
		verifyOneLineSetUp(expectedResult);
	}

	@Test
	public void transformSpaceBetweenReturnsBitBetween() {
		String line = "space inBetween";
		BitSet expectedResult = new BitSet(line.length());
		expectedResult.set(5);
		transformLine(line);
		verifyOneLineSetUp(expectedResult);
	}

	@Test
	public void transformTwoSpacesTogetherReturnsBitsTogether() {
		String line = "two  spaces";
		BitSet expectedResult = new BitSet(line.length());
		expectedResult.set(3);
		expectedResult.set(4);
		transformLine(line);
		verifyOneLineSetUp(expectedResult);
	}

	@Test
	public void transformOnlySpaces() {
		String line = "          ";
		BitSet expectedResult = new BitSet(line.length());
		expectedResult.flip(0, line.length());
		transformLine(line);
		verifyOneLineSetUp(expectedResult);
	}

	@Test
	public void transformMultipleSpaces() {
		String line = BOOK_LINE;
		BitSet expectedResult = createTransformedBookLine();
		transformLine(line);
		verifyOneLineSetUp(expectedResult);
	}

	private BitSet createTransformedBookLine() {
		BitSet expectedResult = new BitSet(BOOK_LINE.length());
		expectedResult.set(5);
		expectedResult.set(11);
		expectedResult.set(17);
		expectedResult.set(21);
		expectedResult.set(27);
		expectedResult.set(39);
		expectedResult.set(51);
		return expectedResult;
	}

	@Test
	public void transformTwoLines() {
		Mockito.when(mockedFile.readLine()).thenReturn(BOOK_LINE, BOOK_LINE,
				(String) null);
		transformedBook = mockedVictim.transformBook();
		verifyLinesTransformed(2);
	}

	private void verifyLinesTransformed(int lines) {
		Assert.assertNotNull(transformedBook);
		Assert.assertEquals(lines, transformedBook.size());
		BitSet transformedLineContent = createTransformedBookLine();
		for (int i = 0; i < transformedBook.size(); i++) {
			Line<BitSet> transformedLine = transformedBook.get(i);
			Assert.assertEquals(transformedLineContent,
					transformedLine.getLineContent());
			Assert.assertEquals(i, transformedLine.getRow());
			Assert.assertEquals(mockedVictim.getId(),
					transformedLine.getBookId());
		}
		Mockito.verify(mockedFile, Mockito.atLeast(lines)).readLine();
	}

	@Test
	public void transformTenLines() {
		Mockito.when(mockedFile.readLine()).thenReturn(BOOK_LINE, BOOK_LINE,
				BOOK_LINE, BOOK_LINE, BOOK_LINE, BOOK_LINE, BOOK_LINE,
				BOOK_LINE, BOOK_LINE, BOOK_LINE, (String) null);
		transformedBook = mockedVictim.transformBook();
		verifyLinesTransformed(10);
	}

}
