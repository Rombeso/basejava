package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected int countResumes = 0;
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected void checkStorageLimit(String uuid) {
        if (countResumes == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        }
    }

    @Override
    protected void doUpdate(Integer index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOf(storage, countResumes));
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
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
    protected boolean isExist(Integer searchKey) {
        return searchKey > -1;
    }
}
