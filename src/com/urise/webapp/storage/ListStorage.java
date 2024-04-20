package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    protected final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    protected boolean checkStorageLimit() {
        return false;
    }

    @Override
    protected void setElementsByIndex(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    protected Resume[] getAllElements() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    protected Resume getElementByIndex(int index) {
        return storage.get(index);
    }

    @Override
    protected void clearAllElements() {
        storage.clear();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resumeToFind = new Resume(uuid);
        return storage.indexOf(resumeToFind);
    }

    @Override
    protected void removeElement(int index) {
        storage.remove(index);
    }

    @Override
    protected void insertElement(Resume r, int index) {
        storage.add(r);
        countResumes++;
    }

}
