package ru.job4j.inout.serialization.json;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/** 4. JAXB. Преобразование XML в POJO
 *
 * @author Shuyski Ruslan
 * @version 1
 */
public class StudentStart {

    public static void main(String[] args) throws Exception {
        Student student = new Student(true, 1,
                new StudentFio("Petrov Ivan"),
                new String[] {"Math", "English", "Physics"});
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Student.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(student, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для сериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // Десереализуем
            Student result = (Student) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}