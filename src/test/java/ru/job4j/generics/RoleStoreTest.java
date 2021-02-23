package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void falseRoleReplace() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("Products");
        Role role2 = new Role("Services");
        Role role3 = new Role("Credit");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        assertThat(roleStore.replace("Rating", new Role("Credits") { }),
                is(false));
    }

    @Test
    public void roleDelete() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("Products");
        Role role2 = new Role("Services");
        Role role3 = new Role("Credit");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        assertThat(roleStore.delete("Credit"), is(true));
    }

    @Test
    public void noUserFindById() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("Products");
        Role role2 = new Role("Services");
        Role role3 = new Role("Credit");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        assertNull(roleStore.findById("Sale"));
    }
}
