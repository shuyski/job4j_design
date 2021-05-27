package ru.job4j.inout.find;

import java.util.HashMap;
import java.util.Map;

/**
 * Class find.Revise
 * принимает
 * массив аргументов и
 * разбивает их на пары:
 * ключ=значение
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class Revise {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            int one = arg.indexOf("-");
            int two = arg.indexOf("=");
            if (one == -1 || two == -1) {
                throw new IllegalArgumentException();
            }
            String key = arg.substring(one + 1, two);
            String value = arg.substring(two + 1);
            if (key.isEmpty() || value.isEmpty()) {
                throw new IllegalArgumentException("No key or value");
            }
            values.put(key, value);
        }
    }

    public static Revise of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments");
        }
        Revise names = new Revise();
        names.parse(args);
        return names;
    }
}

