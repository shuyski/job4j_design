package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Class Config читает файл
 * конфигурации *.properties
 * с парами ключ=значение
 * и добавляет их в HashMap
 *
 * @author Ruslan Shuyski
 * @version 3
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(this.path))) {
        reader
                .lines()
                .filter(t -> !t.isBlank()
                        && t.charAt(0) != '#')
                .forEach(t -> {
                    int index = t.indexOf("=");
                    if (index == -1) {
                        throw new IllegalArgumentException();
                    }
                    String key = t.substring(0, index);
                    String value = t.substring(index + 1);
                    if (key.isEmpty() || value.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    values.put(key, value);
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read =
                     new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("files/Config/app.properties"));
    }
}