package ru.job4j.statistics;

import java.util.*;

/**
 * Class Analize calculation of sheet change statistics
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        info.changed = find(previous, current, true);
        info.added = find(previous, current, false);
        info.deleted = find(current, previous, false);
        return info;
    }

    public int find(List<User> previous, List<User> current, boolean key) {
        int change = 0;
        int addOrDell = 0;
        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.id, user.name);
        }
        for (User user : current) {
            if (!map.containsKey(user.id)) {
                addOrDell++;
            } else {
                if (key && !map.get(user.id).equals(user.name)) {
                    change++;
                }
            }
        }
        return key ? change : addOrDell;
    }

    public static class User {
        int id;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}