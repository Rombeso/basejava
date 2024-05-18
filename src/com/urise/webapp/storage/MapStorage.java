package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<Resume> {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected void doUpdate(Resume resume, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected void clearAllElements() {
        storage.clear();
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }
}
