package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
    SimpleArray<E> set = new SimpleArray<>();

    public boolean add(E e) {
        for (E value : set) {
            if (value.equals(e)) {
                return false;
            }
        }
        set.add(e);
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return Objects.equals(set, simpleSet.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }
}
