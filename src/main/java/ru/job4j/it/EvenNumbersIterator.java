package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {
    private final int[] data;
    private int row = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean chet(int mod) {
        return mod % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        while (row != data.length) {
            if (chet(data[row])) {
                return true;
            }
            row++;
        }
        return false;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row++];
    }
}
