package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {

    public void clear() {
        clearAllElements();
    }

    public int size() {
        return getSize();
    }

    public Object get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public List<Resume> getAllSorted() {
        return doGetAll();
    }

    public void update(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract List<Resume> doGetAll();

    protected abstract Resume doGet(Object searchKey);

    protected abstract void clearAllElements();

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract int getSize();
}
