package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isExist(String uuid) {
        int index = (int) getIndex(uuid);
        if (index > -1) {
            return true;
        }
        return false;
    }

    @Override
    protected void checkStorageLimit(String uuid) {}

    @Override
    protected void setElementsByIndex(Object index, Resume r) {
        storage.set((int) index, r);
    }

    @Override
    protected Resume[] getAllElements() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    protected Resume getElementByIndex(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected void clearAllElements() {
        storage.clear();
    }

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void removeElement(Object index) {
        storage.remove((int) index);
    }

    @Override
    protected void insertElement(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }
}
