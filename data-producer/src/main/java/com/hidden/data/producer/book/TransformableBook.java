package com.hidden.data.producer.book;

import java.util.List;

public interface TransformableBook<K> extends Book<K> {
	List<Line<K>> transformBook();
}
