package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private int modCount = 0;
    private int size = 0;

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        container = check();
        container[size++] = model;
        modCount++;
    }

    public Object[] check() {
        return size == 0 ? new Object[2]
                :
                Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int row = 0;
            @Override
            public boolean hasNext() {
                return row < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if ((expectedModCount != modCount)) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[row++];
            }
        };
    }
}
