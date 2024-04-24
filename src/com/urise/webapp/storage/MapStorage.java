package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void setElementsByIndex(Object index, Resume r) {
        storage.put((String) index, r);
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected Resume getElementByIndex(Object index) {
        return storage.get((String) index);
    }

    @Override
    protected void clearAllElements() {
        storage.clear();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void removeElement(Object index) {
        storage.remove((String) index);
    }

    @Override
    protected void insertElement(Resume r, Object index) {
        storage.put((String) index, r);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }

}
