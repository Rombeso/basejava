package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResumes = 0;
    protected final String RESUME_NOT_PRESENT = "Резюме не найдено, uuid: ";
    protected final String RESUME_PRESENT = "Резюме уже существует, uuid: ";

    public int size() {
        return countResumes;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println(RESUME_NOT_PRESENT + uuid);
            return null;
        }
    }

    protected abstract int getIndex(String uuid);

}
