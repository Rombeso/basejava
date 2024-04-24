package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected int countResumes = 0;
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected void checkStorageLimit(String uuid) {
        if (countResumes == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        }
    }

    @Override
    protected void setElementsByIndex(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    @Override
    protected Resume[] doGetAll() {
        return Arrays.copyOf(storage, countResumes);
    }

    @Override
    protected Resume getElementByIndex(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void clearAllElements() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    @Override
    protected int getSize() {
        return countResumes;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey > -1;
    }
}
