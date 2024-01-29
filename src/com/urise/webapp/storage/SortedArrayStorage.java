package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void addResume(Resume r, int index) {
        int insertionPoint = -index - 1;
        if (insertionPoint > countResumes) {
            storage[countResumes++] = r;
        } else {
            for (int i = countResumes; i >= insertionPoint; i--) {
                if (i == insertionPoint) {
                    storage[i + 1] = storage[i];
                    storage[i] = r;
                } else {
                    storage[i + 1] = storage[i];
                }
            }
            countResumes++;
        }
    }

    @Override
    protected void removeResume(int index) {
        storage[index] = null;
        for (int i = index; i < countResumes; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResumes, searchKey);
    }

}

