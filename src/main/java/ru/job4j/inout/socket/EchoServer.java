package ru.job4j.inout.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class EchoServer
 * реализует работу
 * ServerSocket
 *
 * @author Ruslan Shuyski
 * @version 3
 */
public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream()) {
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                        String str = " ";
                        String answer = "What";
                        while (!(str.isEmpty())) {
                            str = in.readLine();
                            System.out.println(str);
                            if (str.contains("Exit")) {
                                answer = "Bye";
                            }
                            if (str.contains("Hello")) {
                                answer = "Hello, dear friend.";
                            }
                        }
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(answer.getBytes());
                        if (answer.equals("Bye")) {
                            server.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Error:", e);
        }
    }
}