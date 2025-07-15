package ru.iman_burlyq.dao;

import ru.iman_burlyq.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class  ProfileDao {

    private final ConcurrentHashMap<Long, Profile> storage;

    private final AtomicLong idStorage;

    public ProfileDao() {
        this.storage = new ConcurrentHashMap<>();
        this.idStorage = new AtomicLong();
    }

    public Profile save(Profile profile) {
        long id = idStorage.incrementAndGet();
        profile.setId(id);
        storage.put(id, profile);
        System.out.println(storage.values());
        return profile;
    }

    public Optional<Profile> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    // TODO delete, update, findAll
    public void update(Profile profile) {
        Long id = profile.getId();
        if (id == null) return;
        storage.put(id, profile);
    }

    public boolean delete(Long id) {
        return storage.remove(id) != null;
    }

    public List<Profile> findAll() {
        return new ArrayList<>(storage.values());
    }


}
