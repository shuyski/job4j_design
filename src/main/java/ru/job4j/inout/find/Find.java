package ru.job4j.inout.find;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class find.Find
 * осуществляет поиск
 * файлов по критерию
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class Find {

    public static List<Path> find(Revise list, Path dir) throws IOException {
        List<Path> rsl;
        switch (list.get("t")) {
            case "mask":
                rsl = Searcher.search(dir, p -> p.toFile().getName().contains(list.get("n")));
                break;
            case "name":
                rsl = Searcher.search(dir, p -> p.toFile().getName().equals(list.get("n")));
                break;
            case "regex":
                rsl = Searcher.search(dir, p -> p.toFile().getName().matches(list.get("n")));
                break;
            default:
                throw new IllegalArgumentException();
        }
        return rsl;
    }

    public static void write(String file, List<Path> list) {
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            for (Path line : list) {
                out.println(line.toAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Revise array = Revise.of(new String[] {
                args[0], args[1], args[2], args[3]}
                );
        Path dir = Paths.get(array.get("d"));
        List<Path> list = Find.find(array, dir);
        write(array.get("o"), list);
    }
}
