package ru.job4j.inout.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/** 5. Преобразование JSON в POJO. JsonObject
 *
 * @author Shuyski Ruslan
 * @version 1
 */
public class StudentJSONStart {

    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonStudentFIO = new JSONObject("{\"Name\":\"Ivan Petrov\"}");
        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Math");
        list.add("English");
        list.add("Physics");
        JSONArray jsonExams = new JSONArray(list);
        /* JSONObject напрямую методом put */
        final Student student = new Student(true, 1, new StudentFio("Ivan Petrov"), new String[] {"Math", "English", "Physics"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Study", student.isStudy());
        jsonObject.put("Klass", student.getKlass());
        jsonObject.put("SN", jsonStudentFIO);
        jsonObject.put("Exams", jsonExams);
        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(student).toString());
    }
}
