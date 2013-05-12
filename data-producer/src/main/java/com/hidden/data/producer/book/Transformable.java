package com.hidden.data.producer.book;

import java.util.List;

public interface Transformable<K> {
	List<Line<K>> transformBook();
}
