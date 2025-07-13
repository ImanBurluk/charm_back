package ru.iman_burlyq.service;

import ru.iman_burlyq.dao.ProfileDao;
import ru.iman_burlyq.model.Profile;

import java.util.Optional;

public class ProfileService {

    private final ProfileDao dao;

    public ProfileService(ProfileDao dao) {
        this.dao = dao;
    }

    public Profile save(Profile profile ) {
        return dao.save(profile);
    }

    public Optional<Profile> findById(Long id) {
        if(id == null) return Optional.empty();
        return dao.findById(id);
    }

    // TODO delete, update, findAll
}
