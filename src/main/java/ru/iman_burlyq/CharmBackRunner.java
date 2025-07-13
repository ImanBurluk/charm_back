package ru.iman_burlyq;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CharmBackRunner {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream rqStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream rsStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)
        ) {
            while (scanner.hasNext()) {
                String request = scanner.nextLine();
                rqStream.writeUTF(request);
                String response = rsStream.readUTF();
                System.out.println(response);
            }
        }
    }

}