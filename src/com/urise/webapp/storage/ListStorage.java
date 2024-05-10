package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey > -1;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.set((int) searchKey, r);
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> copyStorage = new ArrayList<>(storage);
        copyStorage.stream().sorted(Comparator.comparing(Resume::getFullName)
                .thenComparing(Resume::getUuid)
        ).toList();
        return copyStorage;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((int) searchKey);
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
    protected void doDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }
}
