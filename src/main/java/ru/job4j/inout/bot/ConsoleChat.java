package ru.job4j.inout.bot;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

/**
 * Class ConsoleChat
 * консольный чат с ботом
 * с готовыми фразами в файле
 * botAnswers.txt. Диалог
 * записывается в файл path.txt
 * с кодировкой windows-1251
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        Scanner input = new Scanner(System.in);
        Path in = Paths.get(botAnswers);
        if (!in.toFile().exists()) {
            throw new NoSuchFileException("No file 'botAnswers'");
        }
        try (PrintWriter out = new PrintWriter(
                new FileWriter(this.path, Charset.forName("Windows-1251")))) {
            String i = "Begin";
            System.out.println(i);
            int length = (int) Files.lines(in).count();
            while (!i.equals(OUT)) {
                i = input.nextLine();
                if (i.equals(STOP)) {
                    out.println(i);
                    while (!i.equals(CONTINUE)) {
                        i = input.nextLine();
                    }
                }
                switch (i) {
                    case OUT:
                        out.println(i);
                        System.out.println("End");
                        break;
                    case CONTINUE:
                        out.println(i);
                        break;
                    default:
                        out.println(i);
                        Random r = new Random();
                        String bot = " ";
                        while (bot.isBlank()) {
                        bot = Files.readAllLines(in).get(r.nextInt(length));
                        }
                        System.out.println(bot);
                        out.println(bot);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("files/ConsoleChat/path.txt",
                "files/ConsoleChat/botAnswers.txt");
        cc.run();
    }
}