package ru.job4j.inout.find;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class find.Find
 * осуществляет поиск
 * файлов по критерию
 *
 * @author Ruslan Shuyski
 * @version 2
 */
public class Find {

    public static List<Path> find(Revise list, Path dir) throws IOException {
        List<Path> rsl;
        switch (list.get("t")) {
            case "mask":
                String li = "";
                String lis = "";
                if (list.get("n").contains("*")) {
                    li = list.get("n").replaceAll("\\*", ".+");
                }
                if (li.contains("?")) {
                    lis = li.replaceAll("\\?", ".?");
                }
                Pattern pattern1 = Pattern.compile(lis);
                rsl = regex(dir, pattern1);
                break;
            case "name":
                rsl = Searcher.search(dir, p -> p.toFile().getName().equals(list.get("n")));
                break;
            case "regex":
                Pattern pattern = Pattern.compile(list.get("n"));
                rsl = regex(dir, pattern);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return rsl;
    }

    public static List<Path> regex(Path dir, Pattern regex) throws IOException {
        List<Path> finalRsl = new ArrayList<>();
        Files.walkFileTree(dir, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
                Matcher matcher = regex.matcher(file.toFile().getName());
                if (matcher.find()) {
                    finalRsl.add(file.getFileName());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return finalRsl;
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
