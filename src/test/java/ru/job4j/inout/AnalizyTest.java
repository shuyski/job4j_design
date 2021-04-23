package ru.job4j.inout;

import org.junit.Test;
import java.io.FileNotFoundException;

public class AnalizyTest {

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
}