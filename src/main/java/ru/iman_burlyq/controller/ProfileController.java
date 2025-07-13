package ru.iman_burlyq.controller;

import ru.iman_burlyq.service.ProfileService;

public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void work (String request){
        System.out.println("ProfileController");
    }
}
