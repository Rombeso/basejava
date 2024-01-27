package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{


    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (countResumes == STORAGE_LIMIT) {
            System.out.println("Невозможно сохранить резюме с "
                    + r.getUuid() + ", storage заполнен полностью, максимальное количество резюме - "
                    + STORAGE_LIMIT);
        } else if (index >= 0) {
            System.out.println(RESUME_PRESENT + r.getUuid());
        } else {
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
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = null;
            for (int i = index; i < countResumes; i++) {
                storage[i] = storage[i + 1];
            }
            storage[countResumes - 1] = null;
            countResumes--;
        } else {
            System.out.println(RESUME_NOT_PRESENT + uuid);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResumes, searchKey);
    }

}
