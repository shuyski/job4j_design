package ru.job4j.inout;

import java.io.FileOutputStream;

/**
 * Class Matrix создаёт таблицу умножения
 * и записывает её в файл
 *
 * @author Ruslan Shuyski
 * @version 2
 */
public class Matrix {
    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                table[i - 1][j - 1] = i * j;
            }
        }
        return table;
    }

    public static void main(String[] args) {
        int[][] matrix = Matrix.multiple(5);
        StringBuilder text = new StringBuilder("");
        for (int i = 0; i < matrix.length; i++) {
           text.append(i > 0 ? System.lineSeparator() : "");
            for (int j = 0; j < matrix.length; j++) {
                text.append(matrix[i][j]).append(" ");
            }
        }
        try (FileOutputStream out = new FileOutputStream("MatrixIt.txt")) {
            out.write(text.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
