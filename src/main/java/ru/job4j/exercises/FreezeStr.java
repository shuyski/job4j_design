package ru.job4j.exercises;

/**
 * Class FreezeStr проверка строк на идентичность
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class FreezeStr {
    public static boolean eq(String left, String right) {
        char[] str = new char[left.length()];
                 // создаём два массива размером длин строк
        char[] str1 = new char[right.length()];
        for (int i = 0; i < left.length(); i++) {
            str[i] = left.charAt(i);
                             // добавляем строки посимвольно в наши массивы
            str1[i] = right.charAt(i);
        }
        int q = 0; // счётчик равентства
        for (int j = 0; j < str.length; j++) {
            for (int i = 0; i < str1.length; i++) {
                if (str[i] == str1[j]) {
                    /* если символы равны заменяем символ внутреннего цикла на '0',
                    чтобы он не учитывался в равентсве при дальнейшем сравнении */
                    str[i] = '0';
                    q = q + 1; // увеличиваем счётчик
                    break;
                }
            }
        }
        return q == left.length(); // если счётчик равен длине строки, то истина
    }
}