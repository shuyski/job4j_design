package ru.job4j.exercises;

/**
 * Class Article проверка текста на оригинальность
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class Article {
    public static boolean generateBy(String origin, String line) {
        String[] str1 = origin.split(" "); // создаём массив строк из текстов
        String[] str2 = line.split(" ");
        int q = 0; // счётчик равенства
        for (String s : str2) {
            for (String s1 : str1) {
                if ((s.equals(s1))
                        || ((s + ".").equals(s1))
                        || ((s + ",").equals(s1))
                        || ((s + "!").equals(s1))) {
                    // проверяем на равенство слова
                    // с учётом знаков пунктуации
                    q++; // если равны увеличиваем счётчик
                    break;
                }
            }
        }
        return q == str2.length; // если счётчик равен длине массива
        // второго текста, то истина
    }
}
