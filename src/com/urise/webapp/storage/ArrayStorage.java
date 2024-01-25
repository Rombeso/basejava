package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (countResumes == STORAGE_LIMIT) {
            System.out.println("Невозможно сохранить резюме с "
                    + r.getUuid() + ", storage заполнен полностью, максимальное количество резюме - "
                    + STORAGE_LIMIT);
        } else if (index >= 0) {
            System.out.println(RESUME_PRESENT + r.getUuid());
        } else {
            storage[countResumes++] = r;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[countResumes - 1];
            storage[countResumes - 1] = null;
            countResumes--;
        } else {
            System.out.println(RESUME_NOT_PRESENT + uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResumes);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println(RESUME_NOT_PRESENT + resume.getUuid());
        }
    }
}
