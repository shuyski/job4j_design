package ru.job4j.inout.serialization.json;

public class StudentFio {
    private final String name;

    public StudentFio(String phone) {
        this.name = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='"
                + name
                + '\''
                + '}';
    }
}