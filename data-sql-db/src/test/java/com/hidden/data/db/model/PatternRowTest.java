package com.hidden.data.db.model;

import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.common.reflexion.Reflexion;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class PatternRowTest implements PersistentEntityTestable {

	private static final String X_CHAR = "X";
	private static final String MATCHES_10 = "XXXXXXXXXX";
	private static final PatternRowTest INSTANCE = new PatternRowTest();
	private static final List<PatternItem> ROW_CONTENT = Collections
			.singletonList(PatternItem.createEmptyItem());
	private static final Integer ROW_ID = Integer.valueOf(1);
	private PatternRow victim;
	private List<PatternItem> content;
	private boolean matches;

	public static PatternRowTest getInstance() {
		return INSTANCE;
	}

	@Override
	public PersistentEntity givenNewEntity() {
		givenNewItem();
		return victim;
	}

	private void givenNewItem() {
		victim = new PatternRow();
	}

	@Override
	public PersistentEntity givenExistingEntity() {
		givenExistingItem();
		return victim;
	}

	protected void givenExistingItem() {
		givenNewItem();
		Reflexion.getInstance().setMember(victim, "id", ROW_ID);
		Reflexion.getInstance().setMember(victim, "content", ROW_CONTENT);

	}

	@Test
	public void verifyPersistentEntity() {
		PersistentEntityVerifier verifier = new PersistentEntityVerifier(this);
		verifier.verify();
	}

	@Test
	public void getContent_ReturnsCreationContent() {
		givenExistingItem();
		whenGetContent();
		thenContentShouldBeAsCreation();
	}

	private void whenGetContent() {
		content = victim.getContent();

	}

	private void thenContentShouldBeAsCreation() {
		Assert.assertEquals(ROW_CONTENT, content);
	}

	@Test
	public void matches_startIndexOverLineLength_ReturnsFalse() {
		givenAllXPatterRow(10);
		whenMatches(11, MATCHES_10);
		thenMatchesShouldBe(false);
	}

	private void givenAllXPatterRow(int rowLength) {
		PatternItem saceItem = PatternItem.createEmptyItem();
		Reflexion.getInstance().setMember(saceItem, "value", X_CHAR);
		givenNewItem();
		Reflexion.getInstance().setMember(victim, "content",
				Collections.nCopies(rowLength, saceItem));
	}

	private void whenMatches(int startIndex, String line) {
		matches = victim.matches(startIndex, line);
	}

	private void thenMatchesShouldBe(boolean expectedValue) {
		Assert.assertEquals(expectedValue, matches);
	}

	@Test
	public void matches_PatternLengthOverRemainingLine_ReturnsFalse() {
		givenAllXPatterRow(5);
		whenMatches(6, MATCHES_10);
		thenMatchesShouldBe(false);
	}

	@Test
	public void matches_5PatternEmptyLine_ReturnsFalse() {
		givenAllXPatterRow(5);
		whenMatches(0, StringUtils.EMPTY);
		thenMatchesShouldBe(false);
	}

	@Test
	public void matches_EmptyPatternEmptyLine_ReturnsTrue() {
		givenAllXPatterRow(0);
		whenMatches(0, StringUtils.EMPTY);
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_PatternMatchesLineSameSize_ReturnsTrue() {
		givenAllXPatterRow(10);
		whenMatches(0, MATCHES_10);
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_PatternMatchesLineBiggerSize_ReturnsTrue() {
		givenAllXPatterRow(5);
		whenMatches(0, "XXXXX     ");
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_DoesNotMatchBeginningLineBiggerSize_ReturnsFalse() {
		givenAllXPatterRow(5);
		whenMatches(0, "     XXXXXX");
		thenMatchesShouldBe(false);
	}

	@Test
	public void matches_MatchesEndLineBiggerSize_ReturnsTrue() {
		givenAllXPatterRow(5);
		whenMatches(5, "     XXXXXX");
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_MatchesMiddleLineBiggerSize_ReturnsTrue() {
		givenAllXPatterRow(5);
		whenMatches(2, "  XXXXX   ");
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_Matches1Line1_ReturnsTrue() {
		givenAllXPatterRow(1);
		whenMatches(0, "X");
		thenMatchesShouldBe(true);
	}

}
