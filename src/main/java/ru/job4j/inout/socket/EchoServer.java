package ru.job4j.inout.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class EchoServer
 * реализует работу
 * ServerSocket
 *
 * @author Ruslan Shuyski
 * @version 1
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream()) {
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                        String str = " ";
                        while (!str.isEmpty()) {
                            str = in.readLine();
                            System.out.println(str);
                            out.write("HTTP/1.1 200 OK\r\n".getBytes());
                            if (str.contains("Hello")) {
                                out.write("Hello, dear friend.\r\n".getBytes());
                                break;
                            }
                            if (str.contains("Exit")) {
                                server.close();
                                break;
                            }
                            out.write("What\r\n".getBytes());
                            str = "";
                        }
                    }
                }
            }
        }
    }
}
