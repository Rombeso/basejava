package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int countResumes = 0;

    public void clear() {
        clearAllElements();
        countResumes = 0;
    }


    public int size() {
        return countResumes;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return getElementByIndex(index);
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

    public Resume[] getAll() {
        return getAllElements();
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            setElementsByIndex(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract void setElementsByIndex(int index, Resume resume);

    protected abstract Resume[] getAllElements();

    protected abstract Resume getElementByIndex(int index);

    protected abstract void clearAllElements();

    protected abstract int getIndex(String uuid);

    protected abstract void removeElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
