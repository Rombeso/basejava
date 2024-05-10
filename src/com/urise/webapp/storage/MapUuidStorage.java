package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void doUpdate(Object index, Resume r) {
        storage.put((String) index, r);
    }

    @Override
    protected List<Resume> doGetAll() {
        ArrayList<Resume> resumeList = new ArrayList<>(storage.values());
        resumeList.stream().sorted(Comparator.comparing(Resume::getFullName)
                .thenComparing(Resume::getUuid)
        ).toList();
        return resumeList;
    }

    @Override
    protected Resume doGet(Object index) {
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
    protected void doDelete(Object index) {
        storage.remove((String) index);
    }

    @Override
    protected void doSave(Resume r, Object index) {
        storage.put((String) index, r);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }

}
