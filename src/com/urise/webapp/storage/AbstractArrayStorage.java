package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

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
    public abstract void save(Resume r);
    public abstract void delete(String uuid);


    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

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
