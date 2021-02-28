package ru.job4j.collection;

import java.util.*;

public class LinkArray<E> implements Iterable<E> {
    private Node<E> first;
    private int size = 0;
    private int modCount = 0;

    public void add(E value) {
        Node<E> link = first;
        first = new Node<>(value, null, link);
        first.next = link;
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> value = first;
        for (int i = 0; i < index; i++) {
            value = value.next;
        }
        return value.el;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<E> row = first;
            @Override
            public boolean hasNext() {
                return row != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if ((expectedModCount != modCount)) {
                    throw new ConcurrentModificationException();
                }
                Node<E> rsl = row;
                row = row.next;
                return (E) rsl;
            }
        };
    }

    private static class Node<E> {
        E el;
        Node<E> next;
        Node<E> prev;

        public Node(E el, Node<E> next, Node<E> prev) {
            this.el = el;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{"
                    + "el=" + el
                    + ", next=" + next
                    + ", prev=" + prev
                    + '}';
        }
    }
}
