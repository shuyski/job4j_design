package ru.job4j.map;

import java.util.*;

/**
 * Class HashMapArray realize Map
 *
 * @author Ruslan Shuyski
 * @version 2
 */
public class HashMapArray<K, V> implements Iterable<K>  {
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
            container = new Map[4];
            return;
        }
        Map<K, V>[] maps = container;
        container = new Map[container.length * 2];
        capacity = 0;
        for (Map<K, V> map : maps) {
            if (map != null) {
                container[capacity++] = new Map<>(map.key, map.value);
            }
        }
    }

    public int find(K key) {
        return (container.length - 1) & key.hashCode();
    }

    public V get(K key) {
        int index = find(key);
        for (Map<K, V> map : container) {
            if (map.equals(container[index])) {
                return container[index].value;
            }
        }
        return null;
    }

    public boolean delete(K key) {
        int index = find(key);
        if (index < container.length && container[index] != null) {
            for (Map<K, V> map : container) {
                if (map.equals(container[index])) {
                    container[index] = null;
                    return true;
                }
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
                return container[index] != null;
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
