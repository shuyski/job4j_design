package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    private List<Analize.User> previous = new ArrayList<>();
    private final List<Analize.User> current = new ArrayList<>();
    Analize analize = new Analize();
    Analize.Info info;

    @Before
    public void setUp() {
        Analize.User one = new Analize.User(1, "Andrey");
        Analize.User two = new Analize.User(2, "Pavel");
        Analize.User three = new Analize.User(3, "Albert");
        Analize.User four = new Analize.User(4, "Ruslan");
        Analize.User five = new Analize.User(5, "Alexandr");
        Analize.User six = new Analize.User(6, "Oleg");
        Analize.User seven = new Analize.User(7, "Petr");
        Analize.User eight = new Analize.User(8, "Alexandra");
        Analize.User nine = new Analize.User(9, "Vladimir");
        previous = List.of(one, two, three, four,
                five, six, seven, eight, nine);
        current.addAll(previous);
    }

    @Test
    public void whenAddThreeUser() {
        current.add(new Analize.User(10, "Sergey"));
        current.add(new Analize.User(11, "Ivan"));
        current.add(new Analize.User(12, "Gena"));
        info = analize.diff(previous, current);
        assertThat(info.added, is(3));
    }

    @Test
    public void whenDeleteTwoUser() {
        current.remove(8);
        current.remove(0);
        info = analize.diff(previous, current);
        assertThat(info.deleted, is(2));
    }

    @Test
    public void whenChangeOneUser() {
        current.set(5, new Analize.User(6, "Sergey"));
        info = analize.diff(previous, current);
        assertThat(info.changed, is(1));
    }

    @Test
    public void whenAddNullDelTwo() {
        current.remove(8);
        current.remove(5);
        info = analize.diff(previous, current);
        assertThat(info.deleted, is(2));
    }

    @Test
    public void whenDeleteAndAddOneUser() {
        current.remove(5);
        current.add(new Analize.User(10, "Sergey"));
        info = analize.diff(previous, current);
        assertThat(info.deleted, is(1));
        assertThat(info.added, is(1));
    }

    @Test
    public void whenOneDeleteTwoAddOneChangeUsers() {
        current.remove(5);
        current.add(new Analize.User(10, "Sergey"));
        current.add(new Analize.User(12, "Gena"));
        current.set(2, new Analize.User(3, "Ivan"));
        info = analize.diff(previous, current);
        assertThat(info.deleted, is(1));
        assertThat(info.added, is(2));
        assertThat(info.changed, is(1));
    }

    @Test
    public void whenThreeDeleteFourAddTwoChangeUsers() {
        current.add(new Analize.User(10, "Sergey"));
        current.add(new Analize.User(11, "Gena"));
        current.add(new Analize.User(12, "Georgi"));
        current.add(new Analize.User(13, "Boris"));
        current.set(6, new Analize.User(7, "Van"));
        current.set(2, new Analize.User(3, "Ivan"));
        current.remove(8);
        current.remove(5);
        current.remove(0);
        info = analize.diff(previous, current);
        assertThat(info.deleted, is(3));
        assertThat(info.added, is(4));
        assertThat(info.changed, is(2));
    }
}