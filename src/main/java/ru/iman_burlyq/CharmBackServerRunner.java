package ru.iman_burlyq;

import ru.iman_burlyq.controller.ProfileController;
import ru.iman_burlyq.dao.ProfileDao;
import ru.iman_burlyq.service.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.io.DataInputStream.readUTF;
import static ru.iman_burlyq.model.Commands.*;

public class CharmBackServerRunner {
    public static void main(String[] args) throws IOException {

        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));

        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             DataInputStream requestStream = new DataInputStream(socket.getInputStream());
             DataOutputStream responseStream = new DataOutputStream(socket.getOutputStream());
        ) {
            String request = requestStream.readUTF();
            String response;

            while(!"stop".equals(request)) {
                if(request.startsWith(SAVE.getPrefix())) {
                    response = controller.save(request.split(SAVE.getPrefix())[1]);
                } else if (request.startsWith(FIND_BY_ID.getPrefix())) {
                    response = controller.findById(request.split(FIND_BY_ID.getPrefix())[1]);
                } else if (request.startsWith(FIND_ALL.getPrefix())) {
                    response = controller.findAll();
                } else if (request.startsWith(UPDATE.getPrefix())) {
                    response = controller.update(request.split(UPDATE.getPrefix())[1]);
                } else if (request.startsWith(DELETE.getPrefix())) {
                    response = controller.delete(request.split(DELETE.getPrefix())[1]);
                }  else {
                    response = "Unsupported operation";
                }
                // TODO update, delete, findById, findAll

                responseStream.writeUTF(response);
                request = requestStream.readUTF();
            }
        }
    }
}
