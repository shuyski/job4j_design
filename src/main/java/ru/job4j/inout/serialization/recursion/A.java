package ru.job4j.inout.serialization.recursion;

import org.json.JSONPropertyIgnore;

/** Пример рекурсивного зацикливания.
 *
 * @author Shuyski Ruslan
 * @version 1
 */
public class A {
    private B b;

    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }
}
