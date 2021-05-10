package ru.job4j.inout.bot;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
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
 * @version 2
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
        List<String> inBot;
        List<String> outText = new ArrayList<>();
        Path in = Paths.get(this.botAnswers);
        if (!in.toFile().exists()) {
            throw new NoSuchFileException("No file 'botAnswers'");
        }
        inBot = Files.readAllLines(in);
        String i = "Begin";
        System.out.println(i);
        int length = (int) Files.lines(in).count();
        while (!i.equals(OUT)) {
            i = input.nextLine();
            if (i.equals(STOP)) {
                outText.add(i);
                while (!i.equals(CONTINUE)) {
                    i = input.nextLine();
                }
            }
            switch (i) {
                case OUT:
                    outText.add(i);
                    System.out.println("End");
                    try (PrintWriter out = new PrintWriter(
                            new FileWriter(this.path,
                                    Charset.forName("Windows-1251")))) {
                        for (String line : outText) {
                            out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case CONTINUE:
                    outText.add(i);
                    break;
                default:
                    outText.add(i);
                    Random r = new Random();
                    String bot = " ";
                    while (bot.isBlank()) {
                        bot = inBot.get(r.nextInt(length));
                    }
                    System.out.println(bot);
                    outText.add(bot);
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("files/ConsoleChat/path.txt",
                "files/ConsoleChat/botAnswers.txt");
        cc.run();
    }
}