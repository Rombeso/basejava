package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    protected void doUpdate(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    @Override
    protected List<Resume> doGetAll() {
        Resume[] copyStorage = Arrays.copyOf(storage, countResumes);
        Arrays.sort(copyStorage, Comparator.comparing(Resume::getFullName)
                .thenComparing(Resume::getUuid));
        return Arrays.stream(copyStorage).toList();
    }

    @Override
    protected Resume doGet(Object index) {
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
