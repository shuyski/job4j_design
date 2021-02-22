package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
      mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null) {
            mem.set(mem.indexOf(id), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return  mem.remove(id);
    }

    @Override
    public T findById(String id) {
        return mem.contains(id) ? mem.get(mem.indexOf(id)) : null;
    }
}
