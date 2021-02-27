package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkArrayTest {

    @Test
    public void whenAddThenGet() {
        LinkArray<String> array = new LinkArray<>();
        array.add("first");
        array.add("second");
        Object rsl = array.get(0);
        assertThat(rsl.toString(), is("Node{el=first, next=null, prev=null}"));
    }

    @Test
    public void whenAddThenGetThree() {
        LinkArray<Integer> array = new LinkArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        Object rsl = array.get(2);
        assertThat(rsl.toString(), is("Node{el=3, next=null, "
                + "prev=Node{el=2, next=null, "
                + "prev=Node{el=1, next=null, prev=null}}}"));
    }

    @Test
    public void whenAddThenIt() {
        LinkArray<Object> array = new LinkArray<>();
        array.add("first");
        Object rsl = array.iterator().next();
        assertThat(rsl.toString(), is("Node{el=first, next=null, prev=null}"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        LinkArray<Object> array = new LinkArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        LinkArray<Object> array = new LinkArray<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        LinkArray<Object> array = new LinkArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        LinkArray<String> array = new LinkArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

}