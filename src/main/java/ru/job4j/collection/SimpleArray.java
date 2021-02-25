package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    Object[] container;
    private int modCount = 0;
    private int size = 0;

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size == 0) {
            container = new Object[size + 2];
            container[size++] = model;
            modCount++;
            return;
        }
        Object[] copy = new Object[size + 2];
        System.arraycopy(container, 0, copy, 0, container.length);
        container = new Object[size + 2];
        System.arraycopy(copy, 0, container, 0, copy.length - 1);
        container[size++] = model;
        modCount++;
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
