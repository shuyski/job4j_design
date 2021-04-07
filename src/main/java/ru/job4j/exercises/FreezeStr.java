package ru.job4j.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * Class FreezeStr проверка строк на идентичность
 *
 * @author Ruslan Shuyski
 * @version 2
 */
public class FreezeStr {
    public static boolean eq(String left, String right) {
        return count(left).equals(count(right));
    }

    public static Map<Character, Integer> count(String line) {
        Map<Character, Integer> change = new HashMap<>();
        for (char b : line.toCharArray()) {
            Integer value = change.get(b);
            change.put(b, value == null ? 1 : ++value);
        }
        return change;
    }
}