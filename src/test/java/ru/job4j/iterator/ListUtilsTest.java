package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 1, 4);
        assertThat(Arrays.asList(1, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 10, 15, 2, 3, 11, 5));
        Predicate<Integer> predicate = integer -> integer >= 10;
        ListUtils.removeIf(input, predicate);
        assertThat(Arrays.asList(1, 2, 3, 5), Is.is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 10, 15, 2, 3, 11, 5));
        Predicate<Integer> predicate = integer -> integer >= 10;
        ListUtils.replaceIf(input, predicate, 0);
        assertThat(Arrays.asList(1, 0, 0, 2, 3, 0, 5), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 10, 15, 2, 3, 11, 5));
        List<Integer> element = List.of(10, 9, 5, 6, 15);
        ListUtils.removeAll(input, element);
        assertThat(Arrays.asList(1, 2, 3, 11), Is.is(input));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> element = List.of(10, 9, 5, 6, 15);
        ListUtils.removeAll(input, element);
    }
}