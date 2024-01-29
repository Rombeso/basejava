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

    protected abstract int getIndex(String uuid);
    protected abstract void removeResume(int index);
    protected abstract void addResume(Resume r, int index);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println(RESUME_NOT_PRESENT + uuid);
            return null;
        }
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
            addResume(r, index);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            removeResume(index);
            countResumes--;
        } else {
            System.out.println(RESUME_NOT_PRESENT + uuid);
        }
    }

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
