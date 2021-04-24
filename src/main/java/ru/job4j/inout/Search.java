package ru.job4j.inout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class Search ищет
 * файлы по определённому
 * предикату и добавляет
 * их в ArrayList
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, p -> p
                .toFile()
                .getName()
                .endsWith("js"))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition)
            throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }
}
