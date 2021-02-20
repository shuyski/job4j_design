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

    @Override
    public boolean hasNext() {
        if (data[row].length == 0) {
            row++;
            return false;
        }
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var el = data[row][column];
        column++;
        while (row < data.length && column >= data[row].length) {
            column = 0;
            row++;
        }
        return el;
    }
}
