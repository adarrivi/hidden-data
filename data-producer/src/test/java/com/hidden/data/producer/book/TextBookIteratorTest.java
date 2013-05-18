package com.hidden.data.producer.book;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.common.file.FileLineIterator;
import com.common.test.IteratorTestTemplate;
import com.hidden.data.producer.util.TestObjectFactory;

public class TextBookIteratorTest extends IteratorTestTemplate<Line<String>> {

	private FileLineIterator<String> fileContentIterator;

	@Before
	public void setUp() {
		createVictim();
	}

	@Override
	protected void givenEmptyContent() throws Exception {
		fileContentIterator = TestObjectFactory.getInstance()
				.createEmptyStringArrayIterator();
		createVictim();
	}

	private void createVictim() {
		victim = new TextBookIterator(TestObjectFactory.BOOK_ID,
				fileContentIterator);
	}

	@Override
	protected void givenSingleItemContent() throws Exception {
		String content[] = new String[1];
		content[0] = TestObjectFactory.BOOK_CONTENT[0];
		fileContentIterator = TestObjectFactory.getInstance()
				.createCustomArrayIterator(content);
		createVictim();
	}

	@Override
	protected Line<String> getSingleContentItem() throws Exception {
		Line<String> line = Line.createEmptyLine();
		line.setBookId(TestObjectFactory.BOOK_ID);
		line.setRowContent(TestObjectFactory.BOOK_CONTENT[0]);
		return line;
	}

	@Override
	protected void givenMultipleItemsContent() throws Exception {
		fileContentIterator = TestObjectFactory.getInstance()
				.createStringArrayIterator();
		createVictim();
	}

	@Override
	protected List<Line<String>> getMultipleItemsContent() throws Exception {
		List<Line<String>> lines = new ArrayList<Line<String>>();
		String[] content = TestObjectFactory.BOOK_CONTENT;
		for (int i = 0; i < content.length; i++) {
			Line<String> line = Line.createEmptyLine();
			line.setBookId(TestObjectFactory.BOOK_ID);
			line.setRowContent(content[i]);
			line.setRow(i);
			lines.add(line);
		}
		return lines;
	}

	@Override
	protected Line<String> getNullItem() {
		return Line.createEmptyLine();
	}
}
