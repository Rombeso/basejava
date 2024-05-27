package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey > -1;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume r) {
        storage.set(searchKey, r);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void clearAllElements() {
        storage.clear();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        storage.add(r);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }
}
