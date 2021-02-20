package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    public boolean length(int sum) {
        while (row < data.length && column >= data[row].length) {
            column = 0;
            row++;
            sum++;
        }
        return sum != data.length;
    }

    @Override
    public boolean hasNext() {
        int sum = 0;
        return length(sum);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var el = data[row][column];
        column++;
        return el;
    }
}
