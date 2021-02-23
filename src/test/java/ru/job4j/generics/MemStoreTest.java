package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MemStoreTest {

    @Test
    public void memReplace() {
        MemStore<Base> b = new MemStore<>();
        Base base = new Base("Class") { };
        Base base1 = new Base("Interface") { };
        b.add(base);
        b.add(base1);
        assertThat(b.replace("Class", new Base("Abstract class") { }),
                is(true));
    }

    @Test
    public void memDelete() {
        MemStore<Base> base = new MemStore<>();
        Base b = new Base("Class") { };
        Base b1 = new Base("Interface") { };
        base.add(b);
        base.add(b1);
        assertThat(base.delete("Interface"), is(true));
    }

    @Test
    public void memFindById() {
        MemStore<Base> base = new MemStore<>();
        Base b = new Base("Class") { };
        Base b1 = new Base("Interface") { };
        Base b2 = new Base("Abstract class") { };
        Base b3 = new Base("Interface") { };
        base.add(b);
        base.add(b1);
        base.add(b2);
        base.add(b3);
        assertThat(base.findById("Interface").toString(),
                is("Base{id='Interface'}"));
    }
}