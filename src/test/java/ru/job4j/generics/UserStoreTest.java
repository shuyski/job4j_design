package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void userReplace() {
        UserStore user = new UserStore();
        User u = new User("User");
        User u1 = new User("User1");
        user.add(u);
        user.add(u1);
        assertThat(user.replace("User", new User("Ruslan") { }), is(true));
    }

    @Test
    public void userDelete() {
        UserStore user = new UserStore();
        User u = new User("User");
        User u1 = new User("User1");
        user.add(u);
        user.add(u1);
        assertThat(user.delete("User1"), is(true));
    }

    @Test
    public void userFindById() {
        UserStore user = new UserStore();
        User u = new User("User");
        User u1 = new User("User1");
        User u2 = new User("User2");
        user.add(u);
        user.add(u1);
        user.add(u2);
        assertThat(user.findById("User1").toString(), is("Base{id='User1'}"));
    }

    @Test
    public void userFalseDelete() {
        UserStore user = new UserStore();
        User u = new User("User");
        User u1 = new User("User1");
        user.add(u);
        user.add(u1);
        assertThat(user.delete("Oleg"), is(false));
    }
}