package com.hidden.data.producer.book;

public class Line<T> {

	private int row;
	private int bookId;
	private T rowContent;

	private Line(int row, int bookId, T rowContent) {
		this.row = row;
		this.bookId = bookId;
		this.rowContent = rowContent;
	}

	public static <T> Line<T> createEmptyLine() {
		Line<T> concreteLine = new Line<T>(0, 0, null);
		return concreteLine;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setRowContent(T rowContent) {
		this.rowContent = rowContent;
	}

	public int getRow() {
		return row;
	}

	public int getBookId() {
		return bookId;
	}

	public T getRowContent() {
		return rowContent;
	}

	public boolean isEmpty() {
		return rowContent == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getBookId();
		result = prime * result + getRow();
		result = prime * result
				+ ((getRowContent() == null) ? 0 : getRowContent().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Line)) {
			return false;
		}
		Line<?> other = (Line<?>) obj;
		if (getRowContent() == null && other.getRowContent() != null
				|| other.getRowContent() == null && getRowContent() != null) {
			return false;
		}
		return getBookId() == other.getBookId() && getRow() == other.getRow()
				&& hasEqualsRowContent(other);
	}

	private boolean hasEqualsRowContent(Line<?> other) {
		if (getRowContent() == null) {
			return other.getRowContent() == null;
		}
		return getRowContent().equals(other.getRowContent());
	}

}
