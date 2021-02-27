package ru.job4j.collection;

import java.util.*;

public class LinkArray<E> implements Iterable<E> {
    private Node<E> last;
   // private Node<E> first;
    private int size = 0;
    private int modCount = 0;
    private Object[] container;

    public void add(E value) {
        Node<E> link = last;
        Node<E> newNode = new Node<>(value, null, link);
        last = newNode;
//        if (link == null)
//            first = newNode;
//        else
//            link = newNode;
        if (size == 0) {
            container = new Object[2];
            container[size++] = newNode;
            modCount++;
            return;
        }
        container = Arrays.copyOf(container, container.length * 2);
        container[size++] = newNode;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int row = 0;
            @Override
            public boolean hasNext() {
                return row < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if ((expectedModCount != modCount)) {
                    throw new ConcurrentModificationException();
                }
                return (E) container[row++];
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
