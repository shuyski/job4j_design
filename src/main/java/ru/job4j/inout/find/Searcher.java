package ru.job4j.inout.find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class find.Searcher ищет
 * файлы по определённому
 * предикату и добавляет
 * их в ArrayList
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class Searcher {
    public static List<Path> search(Path root, Predicate<Path> condition)
            throws IOException {
        FileSearch searcher = new FileSearch(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }
}
