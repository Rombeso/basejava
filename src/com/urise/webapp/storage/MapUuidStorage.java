package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void doUpdate(String index, Resume r) {
        storage.put(index, r);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume doGet(String index) {
        return storage.get(index);
    }

    @Override
    protected void clearAllElements() {
        storage.clear();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doDelete(String index) {
        storage.remove(index);
    }

    @Override
    protected void doSave(Resume r, String index) {
        storage.put(index, r);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }

}
