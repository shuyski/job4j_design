package ru.job4j.inout.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

/** 4.2. Поиск дубликатов [#459954]
 * Class DuplicatesVisitor
 * наследует SimpleFileVisitor
 * переопределяет метод visitFile
 * ищет дубликаты файлов проекта
 *
 * @author Shuyski Ruslan
 * @version 1
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> set = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty el = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!set.add(el)) {
            System.out.println(el);
        }
        return super.visitFile(file, attrs);
    }
}