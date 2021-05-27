package ru.job4j.inout.find;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileSearch extends SimpleFileVisitor<Path> {
    private final Predicate<Path> condition;
    private final List<Path> listFile = new ArrayList<>();

    public FileSearch(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        if (condition.test(file.getFileName())) {
            listFile.add(file);
        }
        return CONTINUE;
    }
    public List<Path> getPath() {
        return listFile;
    }
}
