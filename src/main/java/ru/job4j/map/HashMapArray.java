package ru.job4j.map;

import java.util.*;

/**
 * Class HashMapArray realize Map
 *
 * @author Ruslan Shuyski
 * @version 3
 */
public class HashMapArray<K, V> implements Iterable<K>  {
    private static final double LOAD_FACTOR = 0.75;
    private Map<K, V>[] container;
    private int modCount = 0;
    private int capacity = 0;

    public boolean insert(K key, V value) {
        if (capacity == 0) {
            container = new Map[4];
        }
        if (capacity >= container.length * LOAD_FACTOR) {
            extend();
        }
        int index = find(key);
        if (container[index] == null) {
        container[capacity++] = new Map<>(key, value);
        modCount++;
        return true;
        }
        return false;
    }

    public void extend() {
        Map<K, V>[] maps = container;
        container = new Map[container.length * 2];
        capacity = 0;
        for (Map<K, V> map : maps) {
            if (map != null) {
                container[find(map.key)] = new Map<>(map.key, map.value);
            }
        }
    }

    public int find(K key) {
        return (container.length - 1) & key.hashCode();
    }

    public V get(K key) {
        int index = find(key);
        if (container[index] == null || container.length - 1 < index) {
            throw new NoSuchElementException();
        }
        if (key.equals(container[index].key)) {
            return container[index].value;
        }
        return null;
    }

    public boolean delete(K key) {
        int index = find(key);
        if (index < container.length && container[index] != null) {
            if (key.equals(container[index].key)) {
                container[index] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (container == null || index > container.length - 1) {
                    return false;
                }
                if (container[index] == null) {
                    while (container[index] == null && index < container.length - 1) {
                        if (container[++index] != null) {
                            return true;
                        }
                    }
                }
                return index < container.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if ((expectedModCount != modCount)) {
                    throw new ConcurrentModificationException();
                }
                return (K) container[index++];
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
