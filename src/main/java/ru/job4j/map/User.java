package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Ruslan",
                1,
                new GregorianCalendar(1993, Calendar.NOVEMBER, 9));
        User user2 = new User("Ruslan",
                1,
                new GregorianCalendar(1993, Calendar.NOVEMBER, 9));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}' + "\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
