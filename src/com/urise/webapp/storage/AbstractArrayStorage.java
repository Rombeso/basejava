package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    @Override
    protected boolean checkStorageLimit() {
        return countResumes == STORAGE_LIMIT;
    }
    @Override
    protected void setElementsByIndex(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected Resume[] getAllElements() {
        return Arrays.copyOf(storage, countResumes);
    }

    @Override
    protected Resume getElementByIndex(int index) {
        return storage[index];
    }

    @Override
    protected void clearAllElements() {
        Arrays.fill(storage, 0, countResumes, null);
    }
}
