package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume r, int index) {
        int insertionPoint = -index - 1;
        if (insertionPoint > countResumes || insertionPoint == STORAGE_LIMIT - 1) {
            storage[countResumes++] = r;
        } else {
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, (countResumes - insertionPoint));
            storage[insertionPoint] = r;
            countResumes++;
        }
    }
    @Override
    protected void removeElement(int index) {
        storage[index] = null;
        for (int i = index; i < countResumes; i++) {
            storage[i] = storage[i + 1];
        }
    }
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, countResumes, searchKey);
    }

}

