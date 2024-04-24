package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey > -1;
    }

    @Override
    protected void setElementsByIndex(Object index, Resume r) {
        storage.set((int) index, r);
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.toArray(new Resume[0]);
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
    protected Object getSearchKey(String uuid) {
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
