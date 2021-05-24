package ru.job4j.inout.serialization.json;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

/** 4. JAXB. Преобразование XML в POJO
 *
 * @author Shuyski Ruslan
 * @version 1
 */
@XmlElement(value = "SN")
public class StudentFio {

    @XmlAttribute
    private String name;

    public StudentFio() {
    }

    public StudentFio(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SN{"
                + "name='"
                + name
                + '\''
                + '}';
    }

    public String getName() {
        return name;
    }
}