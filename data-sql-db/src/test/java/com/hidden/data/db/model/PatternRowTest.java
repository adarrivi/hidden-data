package com.hidden.data.db.model;

import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.common.reflexion.Reflexion;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class PatternRowTest implements PersistentEntityTestable {

	private static final PatternRowTest INSTANCE = new PatternRowTest();
	private static final List<PatternItem> ROW_CONTENT = Collections
			.singletonList(PatternItem.createEmptyItem());
	private static final Integer ROW_ID = Integer.valueOf(1);
	private PatternRow victim;
	private List<PatternItem> content;

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
}
