package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void clear() {
        clearAllElements();
    }

    public int size() {
        return getSize();
    }

    public Object get(String uuid) {
        if (isExist(uuid)) {
            Object searchKey = getIndex(uuid);
            return getElementByIndex(searchKey);
        } else {
            return getNotExistingSearchKey(uuid);
        }
    }

    public void save(Resume r) {
        checkStorageLimit(r.getUuid());
        if (isExist(r.getUuid())) {
            getExistingSearchKey(r.getUuid());
        } else {
            Object searchKey = getIndex(r.getUuid());
            insertElement(r, searchKey);
        }
    }

    public void delete(String uuid) {
        if (isExist(uuid)) {
            Object searchKey = getIndex(uuid);
            removeElement(searchKey);
        } else {
            getNotExistingSearchKey(uuid);
        }
    }

    public Resume[] getAll() {
        return getAllElements();
    }

    public void update(Resume r) {
        if (isExist(r.getUuid())) {
            Object searchKey = getIndex(r.getUuid());
            setElementsByIndex(searchKey, r);
        } else {
            getNotExistingSearchKey(r.getUuid());
        }
    }

    private Object getExistingSearchKey(String uuid) {
        throw new ExistStorageException(uuid);
    }

    private Object getNotExistingSearchKey(String uuid) {
        throw new NotExistStorageException(uuid);
    }

    protected abstract boolean isExist(String uuid);

    protected abstract void checkStorageLimit(String uuid);

    protected abstract void setElementsByIndex(Object index, Resume resume);

    protected abstract Resume[] getAllElements();

    protected abstract Resume getElementByIndex(Object index);

    protected abstract void clearAllElements();

    protected abstract Object getIndex(String uuid);

    protected abstract void removeElement(Object index);

    protected abstract void insertElement(Resume r, Object index);

    protected abstract int getSize();
}
