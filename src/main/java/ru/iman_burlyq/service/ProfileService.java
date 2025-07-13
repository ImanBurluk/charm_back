package ru.iman_burlyq.service;

import ru.iman_burlyq.dao.ProfileDao;
import ru.iman_burlyq.model.Profile;

import java.util.List;
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
    public Optional<Profile> update(Profile profile) {
        if (profile == null || profile.getId() == null) return Optional.empty();
        return dao.update(profile);
    }

    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }

        return dao.delete(id);
    }


    public List<Profile> findAll() {
        return dao.findAll();
    }

}
