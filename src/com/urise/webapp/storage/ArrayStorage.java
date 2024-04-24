package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Resume r, Object index) {
        checkStorageLimit(r.getUuid());
        storage[countResumes++] = r;
    }

    @Override
    protected void removeElement(Object index) {
        storage[(int) index] = storage[countResumes-- - 1];
    }
}
