package ru.job4j.inout;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class Zip утилита
 * для архивации каталога
 * в zip
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class Zip {
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName zipArg = ArgsName.of(new String[]
                {args[0], args[1], args[2]});
        Path zip = Paths.get(zipArg.get("d"));
        List<Path> list = Search.search(zip, p -> !p
                    .toFile()
                    .getName()
                    .endsWith(zipArg.get("e")));
        List<File> fileList = new ArrayList<>();
        for (Path path : list) {
            fileList.add(path.toFile());
        }
        new Zip().packFiles(fileList, new File(zipArg.get("o")));
        new Zip().packSingleFile(
                new File("pom.xml"),
                new File("C:/projects/job4j_design/files/Zip/pom.zip")
        );
    }
}