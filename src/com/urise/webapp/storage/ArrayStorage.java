package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        checkStorageLimit(r.getUuid());
        storage[countResumes++] = r;
    }

    @Override
    protected void doDelete(Integer index) {
        storage[index] = storage[countResumes-- - 1];
    }
}
