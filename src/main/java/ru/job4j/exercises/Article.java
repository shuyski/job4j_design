package ru.job4j.exercises;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class Article проверка текста на оригинальность
 *
 * @author Ruslan Shuyski
 * @version 2
 */
public class Article {
    public static boolean generateBy(String origin, String line) {
        String[] str1 = origin.split("[ .!,]+");
        String[] str2 = line.split("[ .!,]+");
        Set<String> change = Arrays.stream(str1).collect(Collectors.toSet());
        return change.containsAll(Arrays.asList(str2));
    }
}
