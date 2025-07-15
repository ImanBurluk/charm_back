package ru.iman_burlyq.controller;

import ru.iman_burlyq.model.Profile;
import ru.iman_burlyq.service.ProfileService;

public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService profileService) {

        this.service = profileService;
    }

    public void work(String request) {

        System.out.println("ProfileController");
    }

    public String save(String save) {
        String[] params = save.split(",");
        //06-45
        if(params.length < 4) return "Bad request";

        Profile profile = new Profile();
        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);

        return service.save(profile).toString();

    }
}
