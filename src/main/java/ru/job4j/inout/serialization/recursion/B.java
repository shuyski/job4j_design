package ru.job4j.inout.serialization.recursion;

import org.json.JSONObject;

/** Пример рекурсивного зацикливания.
 *
 * @author Shuyski Ruslan
 * @version 1
 */
public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);

        System.out.println(new JSONObject(b));
    }
}
