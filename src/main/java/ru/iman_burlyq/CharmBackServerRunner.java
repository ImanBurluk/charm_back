package ru.iman_burlyq;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.io.DataInputStream.readUTF;

public class CharmBackServerRunner {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             DataInputStream requestStream = new DataInputStream(socket.getInputStream());
             DataOutputStream responseStream = new DataOutputStream(socket.getOutputStream());
             // TODO (yakv, 2025-07-13) make chat (Scanner)
             Scanner scanner = new Scanner(System.in)
        ) {
            String request = requestStream.readUTF();

            while(!"stop".equals(request)) {
                System.out.println("Client request: " + request);
                String response = scanner.nextLine();
                responseStream.writeUTF(response);
                request = requestStream.readUTF();
            }
        }
    }
}
