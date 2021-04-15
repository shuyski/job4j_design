package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class LogFilter читает файл
 *  и возвращает, где предпоследнее число - это 404
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class LogFilter {

    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        StringBuilder text = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            String[] element = line.split(" ");
            if (EvenNumberFile.isNumeric(element[element.length - 1])
                    && Integer.parseInt(element[element.length - 2]) == 404) {
                list.add(line);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}