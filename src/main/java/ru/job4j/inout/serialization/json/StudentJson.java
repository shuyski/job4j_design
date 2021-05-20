package ru.job4j.inout.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class StudentJson {
    private final boolean study;
    private final int klass;
    private final StudentFio name;
    private final String[] exam;


    public StudentJson(boolean study, int klass, StudentFio name, String[] exam) {
        this.study = study;
        this.klass = klass;
        this.name = name;
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "StudentJson{" + "study="
                + study + ", klass="
                + klass + ", name="
                + name + ", examSubject="
                + Arrays.toString(exam) + '}';
    }

    public static void main(String[] args) {
        final StudentJson student = new StudentJson(true, 1,
                new StudentFio("Petrov Ivan"),
                new String[] {"Math", "English", "Physics"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));
        final String studentJson = "{"
                + "\"study\":true,"
                + "\"klass\": 1,"
                + "\"name\":"
                + "{"
                + "\"SN\":\"Petrov Ivan\""
                + "},"
                + "\"exam\":"
                + "[\"Math\", \"English\", \"Physics\"]"
                + "}";
        final StudentJson studentGson = gson.fromJson(studentJson,
                StudentJson.class);
        System.out.println(studentGson);
    }
}