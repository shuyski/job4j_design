package ru.job4j.inout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class LogFilter читает файл
 *  и возвращает, где предпоследнее число - это 404
 *  и записывает результат в файл
 *
 * @author Ruslan Shuyski
 * @version 2
 */
public class LogFilter {

    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = in
                    .lines()
                    .filter(s ->
                            s.contains("404 ")
                                    && !s.contains("404 -")
                                   )
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String line : log) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        LogFilter.save(log, "LogFilterSave.txt");
        System.out.println(log);
    }
}