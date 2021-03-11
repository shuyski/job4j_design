package ru.job4j.collection;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAdd() {
        SimpleSet<String> s = new SimpleSet<>();
        s.add("one");
        s.add("two");
        s.add("three");
        assertThat(s.iterator().next(), is("one"));
    }

    @Test
    public void whenAddClone() {
        SimpleSet<Integer> s = new SimpleSet<>();
        s.add(1);
        s.add(2);
        s.add(3);
        assertThat(s.add(2), is(false));
    }

    @Test
    public void whenAddCloneObject() {
        SimpleSet<Object> s = new SimpleSet<>();
        s.add("One");
        s.add(2);
        s.add(3.14);
        assertThat(s.add("3.14"), is(true));
        assertThat(s.add(2), is(false));
        assertThat(s.add("One"), is(false));
        assertThat(s.add("3.14"), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        SimpleSet<String> s = new SimpleSet<>();
        s.iterator().next();
    }

    @Test
    public void whenNull() {
        SimpleSet<Integer> s = new SimpleSet<>();
        s.add(null);
        assertThat(s.add(null), is(false));
        assertThat(s.add(3), is(true));
    }
}