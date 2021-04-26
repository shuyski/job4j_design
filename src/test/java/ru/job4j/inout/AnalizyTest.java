package ru.job4j.inout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test() throws FileNotFoundException {
        String target = "files/Analizy/target.csv";
        String source = "files/Analizy/source.log";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
    }

    @Test
    public void notFile() throws FileNotFoundException {
    String target = "files/Analizy/target.csv";
    String source = "files/Analizy/sourc.log";
    Analizy analizy = new Analizy();
    analizy.unavailable(source, target);
    }

    @Test
    public void whenTempFolderError() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.csv");
        Analizy analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(new PrintWriter(source))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;"));
    }

    @Test
    public void whenTempFolderEmpty() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.csv");
        Analizy analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(new PrintWriter(source))) {
            out.println("200 10:56:01");
            out.println("300 10:57:01");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl.toString(), is(""));
    }
}