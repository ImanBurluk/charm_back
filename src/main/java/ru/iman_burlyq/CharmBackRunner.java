package ru.iman_burlyq;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CharmBackRunner {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("yandex.ru", 80);
             DataOutputStream rqStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream rsStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)
        ) {
            while (scanner.hasNext()) {
                String request = scanner.nextLine();
                rqStream.writeUTF(request);
                byte[] bytes = rsStream.readAllBytes();
                System.out.println(new String(bytes));
            }
        }
    }

}