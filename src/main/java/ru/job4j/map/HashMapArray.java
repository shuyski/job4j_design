package ru.job4j.map;

import java.util.*;

public class HashMapArray<K, V> implements Iterable<HashMapArray.Map>  {
    private Map<K, V>[] container;
    private int modCount = 0;
    private int capacity = 0;

    public boolean insert(K key, V value) {
        extend();
        if (container[find(key)] == null) {
        container[capacity++] = new Map<>(key, value);
        modCount++;
        return true;
        }
        return false;
    }

    public void extend() {
        if (capacity == 0) {
            container = new Map[5];
        }
        if (capacity == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    public int find(K key) {
        return container.length - 1 & key.hashCode();
    }

    public V get(K key) {
        int index = find(key);
        return container[index].value;
    }

    public boolean delete(K key) {
        int index = find(key);
        if (index < container.length && container[index] != null) {
            container[index] = null;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Map> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (container == null || index > container.length - 1) {
                    return false;
                }
                return container[index] != null;
            }

            @Override
            public Map<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if ((expectedModCount != modCount)) {
                    throw new ConcurrentModificationException();
                }
                return container[index++];
            }
        };
    }

    static class Map<K, V> {
        K key;
        V value;

        Map(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
