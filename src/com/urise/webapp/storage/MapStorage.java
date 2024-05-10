package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected void doUpdate(Object resume, Resume r) {
        storage.put(r.getUuid(), r);
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
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void clearAllElements() {
        storage.clear();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doDelete(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }

    @Override
    protected void doSave(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }
}
