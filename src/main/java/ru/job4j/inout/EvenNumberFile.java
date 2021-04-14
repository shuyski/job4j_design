package ru.job4j.inout;

import java.io.FileInputStream;

/**
 * Class EvenNumberFile читает из файла
 * число и проверяет его на чётность
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class EvenNumberFile {
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            if (EvenNumberFile.isNumeric(line)
                    && Integer.parseInt(line) % 2 == 0) {
                System.out.println(line);
            }
        }
    }
}
