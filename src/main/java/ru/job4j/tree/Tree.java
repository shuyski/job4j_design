package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 * Class Tree<E> realize TreeMap
 *
 * @author Ruslan Shuyski
 * @version 3
 */
public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> p = findBy(parent);
        if (p.isPresent()) {
            if (findBy(child).isEmpty()) {
                p.get().children.add(new Node<>(child));
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> nodePredicate = t -> t.value.equals(value);
        return findByPredicate(nodePredicate);
    }

    public boolean isBinary() {
        Predicate<Node<E>> nodePredicate = t -> t.children.size() > 2;
        return findByPredicate(nodePredicate).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                return Optional.of(el);
            }
            data.addAll(el.children);
        }
        return Optional.empty();
    }
}
