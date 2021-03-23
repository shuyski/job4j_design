package ru.job4j.map;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HashMapArrayTest {

    @Test
    public void whenInsertAndGet() {
        HashMapArray<String, Integer> map = new HashMapArray<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 15);
        assertThat(map.get("first"), is(1));
    }

    @Test
    public void whenInsertFalse() {
        HashMapArray<String, Integer> map = new HashMapArray<>();
        map.insert("first", 1);
        map.insert("second", 2);
        assertThat(map.insert("first", 3), is(false));
    }

    @Test
    public void whenDeleteTrue() {
        HashMapArray<String, Integer> map = new HashMapArray<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 15);
        assertThat(map.delete("first"), is(true));
    }

    @Test
    public void whenDeleteFalse() {
        HashMapArray<String, Integer> map = new HashMapArray<>();
        map.insert("first", 1);
        map.insert("second", 2);
        assertThat(map.delete("third"), is(false));
    }

    @Test
    public void whenIteratorNextFalse() {
        HashMapArray<String, Integer> map = new HashMapArray<>();
        map.insert("first", 1);
        map.insert("second", 2);
        Iterator<String> iterator = map.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenIteratorNextTrue() {
        HashMapArray<String, Integer> map = new HashMapArray<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 15);
        Iterator<String> iterator = map.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
    }
}