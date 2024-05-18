package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    //protected final Logger log = Logger.getLogger(getClass().getName());
    protected final Comparator<Resume> getResumeComparator() {
        return Comparator.comparing(Resume::getFullName)
                .thenComparing(Resume::getUuid);
    }
    public void clear() {
        clearAllElements();
    }

    public int size() {
        return getSize();
    }

    public Resume get(String uuid) {
        LOG.info("Get: " + uuid);
        SK searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public void save(Resume r) {
        LOG.info("Save: " + r);
        SK searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete: " + uuid);
        SK searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        list.sort(getResumeComparator());
        LOG.info("getAllSorted: size " + list.size());
        return list;
    }

    public void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    private SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        } else {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract List<Resume> doCopyAll();

    protected abstract Resume doGet(SK searchKey);

    protected abstract void clearAllElements();

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract int getSize();
}
