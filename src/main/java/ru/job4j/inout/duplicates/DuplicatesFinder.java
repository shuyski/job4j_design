package ru.job4j.inout.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** 4.2. Поиск дубликатов [#459954]
 * Class Duplicate Finder
 * запускает обход по дереву
 * файлов проекта
 *
 * @author Shuyski Ruslan
 * @version 1
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
    }
}