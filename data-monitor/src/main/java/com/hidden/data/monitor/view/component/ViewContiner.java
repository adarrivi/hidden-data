package com.hidden.data.monitor.view.component;

public interface ViewContiner<K> extends ViewComponent<K> {

	<T extends K> void addComponent(T component);
}
