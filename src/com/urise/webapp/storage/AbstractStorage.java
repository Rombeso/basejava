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
        Object searchKey = getExistingSearchKey(uuid);
        return getElementByIndex(searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        insertElement(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        removeElement(searchKey);
    }

    public Resume[] getAll() {
        return doGetAll();
    }

    public void update(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        setElementsByIndex(searchKey, r);
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(isExist(searchKey)) {
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)) {
            return searchKey;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void setElementsByIndex(Object index, Resume resume);

    protected abstract Resume[] doGetAll();

    protected abstract Resume getElementByIndex(Object index);

    protected abstract void clearAllElements();

    protected abstract Object getSearchKey(String uuid);

    protected abstract void removeElement(Object index);

    protected abstract void insertElement(Resume r, Object index);

    protected abstract int getSize();
}
