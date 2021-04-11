package ru.job4j.statistics;

import java.util.*;

/**
 * Class Analize calculation of sheet change statistics
 *
 * @author Ruslan Shuyski
 * @version 3
 */
public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> map = new HashMap<>();
        for (User user : current) {
            map.put(user.getId(), user);
        }
        for (User user : previous) {
            if (!map.containsKey(user.getId())) {
                info.deleted++;
            } else {
                if (!map.get(user.getId()).equals(user)) {
                    info.changed++;
                }
            }
            map.put(user.getId(), user);
        }
        info.added = map.size() - previous.size();
        return info;
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

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}