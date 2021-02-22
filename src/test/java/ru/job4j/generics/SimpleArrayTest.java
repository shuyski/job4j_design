package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void addString() {
        SimpleArray<String> str = new SimpleArray();
        str.add("Cast");
        str.add("semi");
        str.add("Test");
        assertThat(str.get(0), is("Cast"));
    }

    @Test
    public void set() {
        SimpleArray<Integer> in = new SimpleArray<>();
        in.add(1);
        in.add(2);
        in.add(32);
        in.set(2, 3);
        assertThat(in.get(2), is(3));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void remove() {
        SimpleArray<Object> obj = new SimpleArray();
        obj.add("iPhone");
        obj.add(12);
        obj.add(27.8);
        obj.remove(2);
        obj.get(2);
    }

    @Test
    public void iterator() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        Iterator<Integer> iterator = integers.iterator();
        integers.add(3);
        integers.add(2);
        integers.add(1);
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(false));
    }
}