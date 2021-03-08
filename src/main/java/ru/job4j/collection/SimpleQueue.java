package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private  int size = 0;

    public T poll() {
        transfer();
        size--;
        return out.pop();
    }

    public void transfer() {
        T only = null;
        if ((!out.isEmpty()) && (size > 1)) {
            only = out.pop();
        }
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
        }
        if (only != null) {
            out.push(only);
        }
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}