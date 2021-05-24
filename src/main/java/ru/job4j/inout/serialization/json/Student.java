package ru.job4j.inout.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

/** 4. JAXB. Преобразование XML в POJO
 *
 * @author Shuyski Ruslan
 * @version 1
 */
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private boolean study;

    @XmlAttribute
    private int klass;
    private StudentFio name;
    private String[] exam;

    public Student() { }

    public Student(boolean study, int klass, StudentFio name, String[] exam) {
        this.study = study;
        this.klass = klass;
        this.name = name;
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "Student{" + "study="
                + study + ", klass="
                + klass + ", name="
                + name + ", examSubject="
                + Arrays.toString(exam) + '}';
    }

    public static void main(String[] args) {
        final Student stud = new Student(true, 1,
                new StudentFio("Petrov Ivan"),
                new String[] {"Math", "English", "Physics"});
    }

    public boolean isStudy() {
        return study;
    }

    public int getKlass() {
        return klass;
    }

    public StudentFio getName() {
        return name;
    }

    public String[] getExam() {
        return exam;
    }
}
