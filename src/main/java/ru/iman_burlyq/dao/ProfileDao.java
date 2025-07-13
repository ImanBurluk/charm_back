package ru.iman_burlyq.dao;

import ru.iman_burlyq.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {

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
        return profile;
    }

    public Optional<Profile> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    // TODO delete, update, findAll
    public Optional<Profile> update(Profile profile) {
        Long id = profile.getId();
        if (id == null || !storage.containsKey(id)) {
            return Optional.empty();
        }
        storage.put(id, profile); // заменяем старый профиль
        return Optional.of(profile);
    }

    public boolean delete(Long id) {
        return storage.remove(id) != null;
    }

    public List<Profile> findAll() {
        return new ArrayList<>(storage.values());
    }


}
