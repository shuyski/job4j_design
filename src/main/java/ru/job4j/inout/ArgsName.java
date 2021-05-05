package ru.job4j.inout;

import java.util.HashMap;
import java.util.Map;
/**
 * Class ArgsName принимает
 * массив аргументов и
 * разбивает их на пары:
 * ключ=значение *
 *
 * @author Ruslan Shuyski
 * @version 3
 */
public class ArgsName {
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

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
