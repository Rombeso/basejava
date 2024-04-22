package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected final HashMap<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void checkStorageLimit(String uuid) { }

    @Override
    protected void setElementsByIndex(Object index, Resume r) {
        storage.put((String) index, r);
    }

    @Override
    protected Resume[] getAllElements() {
        Resume[] arrayResume = new Resume[storage.size()];
        int count = 0;
        for (Map.Entry<String, Resume> pair : storage.entrySet()) {
            Resume value = pair.getValue();
            arrayResume[count++] = value;
        }
        return arrayResume;
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
    protected Object getIndex(String uuid) {
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
