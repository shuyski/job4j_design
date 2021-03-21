package ru.job4j.map;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void whenWePrintMap() {
        User first = new User("Petya", 5,
                new GregorianCalendar(1989, Calendar.DECEMBER, 16));
        User second = new User("Petya", 5,
                new GregorianCalendar(1989, Calendar.DECEMBER, 16));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        assertEquals(first.hashCode(), second.hashCode());
    }
}