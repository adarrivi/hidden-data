package com.hidden.data.producer.db;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.db.model.Book;
import com.hidden.data.producer.SpaceBook;

public class SpaceBookDbTest {

	private static final String[] BOOK_CONTENT = {
			"Lorem ipsum dolor sit amet,",
			" consectetur adipisicing elit, sed ",
			"do eiusmod tempor incididunt ", "ut labore et dolore magna ",
			"aliqua." };

	private SpaceBook victim;
	@Mock
	private Book book;
	private List<boolean[]> lines;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getLines_OneLineEmpty_ReturnsOneLineList() {
		givenBookWithContent(StringUtils.EMPTY);
		whenGetLines();
		thenLinesSizeShouldBe(1);
	}

	private void givenBookWithContent(String content) {
		Mockito.when(book.getContent()).thenReturn(content);
		createVictim();
	}

	private void createVictim() {
		victim = new SpaceBookDb(book);
	}

	private void whenGetLines() {
		lines = victim.getLines();
	}

	private void thenLinesSizeShouldBe(int expectedlinesSize) {
		Assert.assertEquals(expectedlinesSize, lines.size());
	}

	@Test
	public void getLines_DifferentNumberOfLines_ReturnsTheSameNumberOfLines() {
		for (int i = 1; i <= BOOK_CONTENT.length; i++) {
			testGetLines_NumberOfLines(i);
		}
	}

	private void testGetLines_NumberOfLines(int numberOfLines) {
		givenBookWithContent(getOneLineContent(numberOfLines));
		whenGetLines();
		thenLinesSizeShouldBe(numberOfLines);

	}

	private String getOneLineContent(int numberOfLines) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfLines; i++) {
			sb.append(BOOK_CONTENT[i]);
			sb.append(SpaceBookDb.LINE_BREAK);
		}
		return sb.toString();
	}

	@Test
	public void getLines_EmptyLine_ReturnsEmptyLine() {
		testGetOneLineResult(StringUtils.EMPTY, new boolean[0]);
	}

	private void testGetOneLineResult(String line, boolean[] expectedResult) {
		givenBookWithContent(line);
		whenGetLines();
		thenFirstLineShouldBe(expectedResult);
	}

	private void thenFirstLineShouldBe(boolean[] bitArrayExpected) {
		boolean[] firstLine = lines.get(0);
		Assert.assertTrue(Arrays.equals(bitArrayExpected, firstLine));

	}

	@Test
	public void getLines_OneCharLine_ReturnsOneFalseBitLine() {
		boolean[] expectedResult = new boolean[] { false };
		testGetOneLineResult("a", expectedResult);
	}

	@Test
	public void getLines_TwoCharLine_ReturnsTwoFalseBitLine() {
		boolean[] expectedResult = new boolean[] { false, false };
		testGetOneLineResult("ab", expectedResult);

	}

	@Test
	public void getLines_FiveCharLine_ReturnsFiveFalseBitLine() {
		boolean[] expectedResult = new boolean[] { false, false, false, false,
				false };
		testGetOneLineResult("abcde", expectedResult);
	}

	@Test
	public void getLines_OneSpace_ReturnsOneTrueBitLine() {
		boolean[] expectedResult = new boolean[] { true };
		testGetOneLineResult(" ", expectedResult);
	}

	@Test
	public void getLines_TwoSpaces_ReturnsTwoTrueBitLine() {
		boolean[] expectedResult = new boolean[] { true, true };
		testGetOneLineResult("  ", expectedResult);
	}

	@Test
	public void getLines_FiveSpaces_ReturnsFiveTrueBitLine() {
		boolean[] expectedResult = new boolean[] { true, true, true, true, true };
		testGetOneLineResult("     ", expectedResult);
	}

	@Test
	public void getLines_OneSpaceBefore_ReturnsOneTrueBitBeforeLine() {
		boolean[] expectedResult = new boolean[] { true, false, false, false };
		testGetOneLineResult(" abc", expectedResult);
	}

	@Test
	public void getLines_OneSpaceBetween_ReturnsOneTrueBitBetweenLine() {
		boolean[] expectedResult = new boolean[] { false, true, false, false };
		testGetOneLineResult("a bc", expectedResult);
	}

	@Test
	public void getLines_OneSpaceEnd_ReturnsOneTrueBitEndLine() {
		boolean[] expectedResult = new boolean[] { false, false, false, true };
		testGetOneLineResult("abc ", expectedResult);
	}

	@Test
	public void getLines_Twice_ReturnsSameResult() {
		boolean[] expectedResult = new boolean[] { false, false, false, true };
		givenBookWithContent("abc ");
		whenGetLines();
		whenGetLines();
		thenFirstLineShouldBe(expectedResult);
	}

}
