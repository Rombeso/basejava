package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int countResumes = 0;

    public int size() {
        return countResumes;
    }
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (countResumes == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(r, index);
        }
    }
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            removeElement(index);
            countResumes--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResumes);
    }
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract int getIndex(String uuid);
    protected abstract void removeElement(int index);
    protected abstract void insertElement(Resume r, int index);

}
