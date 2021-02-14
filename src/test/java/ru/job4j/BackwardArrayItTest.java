package ru.job4j;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BackwardArrayItTest {

    @Test
    public void whenM() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
                );
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenR() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
                );
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNotElement() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
                );
        it.next();
    }
}