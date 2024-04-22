package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume r, Object index) {
        int insertionPoint = -(int) index - 1;
        if (insertionPoint > countResumes || insertionPoint == STORAGE_LIMIT - 1) {
            storage[countResumes++] = r;
        } else {
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, (countResumes - insertionPoint));
            storage[insertionPoint] = r;
            countResumes++;
        }
    }

    @Override
    protected void removeElement(Object index) {
        storage[(int) index] = null;
        for (int i = (int) index; i < countResumes; i++) {
            storage[i] = storage[i + 1];
        }
        countResumes--;
    }

    @Override
    protected Object getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, countResumes, searchKey);
    }

}

