package ru.iman_burlyq.controller;

import ru.iman_burlyq.model.Profile;
import ru.iman_burlyq.service.ProfileService;

import java.util.Optional;

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

    public String findById(String request) {
       String[] strings = request.split(",");
       if(strings.length != 1) return "Bad request: need one number parameter";

       long  id;
       try{
           id = Long.parseLong(strings[0]);
       } catch(NumberFormatException e){
           return "Bad request: can`t parse string [" + strings[0] + "] to long";
       }

       Optional<Profile> maybeProfile = service.findById(id);

       if(maybeProfile.isEmpty()) return "Not found";

       return maybeProfile.get().toString();
    }

    public String findAll() {
        return service.findAll().toString();
    }

    public String update(String request) {
        String[] params = request.split(",");
        if(params.length !=5) return "Bad request: need 5 parameters to update profile";

        long  id;
        try{
            id = Long.parseLong(params[0]);
        } catch(NumberFormatException e){
            return "Bad request: can`t parse string [" + params[0] + "] to long";
        }

        Profile profile = new Profile();
        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);

        service.update(profile);

        return "update successfully";
    }

    public String delete(String request) {
        String[] params = request.split(",");
        if(params.length != 1) return "Bad request: need one number parameter";

        long  id;
        try{
            id = Long.parseLong(params[0]);
        } catch(NumberFormatException e){
            return "Bad request: can`t parse string [" + params[0] + "] to long";
        }

        boolean result = service.delete(id);

        if(!result) return "Not found";

        return "delete successfully";
    }

}
