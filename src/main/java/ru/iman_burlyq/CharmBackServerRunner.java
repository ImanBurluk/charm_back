package ru.iman_burlyq;

import ru.iman_burlyq.controller.ProfileController;
import ru.iman_burlyq.dao.ProfileDao;
import ru.iman_burlyq.service.ProfileService;

import java.io.IOException;

public class CharmBackServerRunner {
    public static void main(String[] args) throws IOException {

        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));
        CharmHttpServer server = new CharmHttpServer(5);
        server.start();
    }
}
