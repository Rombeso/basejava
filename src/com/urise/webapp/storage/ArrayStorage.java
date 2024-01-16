package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int maxNumberResume = 10000;
    private Resume[] storage = new Resume[maxNumberResume];
    private int countResumes;
    private final String RESUME_NOT_PRESENT = "Резюме не найдено, uuid: ";
    private final String RESUME_PRESENT = "Резюме уже существует, uuid: ";


    private int getIndexResume(String uuid) {
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
        int index = getIndexResume(r.getUuid());

        if (countResumes == maxNumberResume) {
            System.out.println("Невозможно сохранить резюме с "
                    + r.getUuid() + ", storage заполнен полностью, максимальное количество резюме - "
                    + maxNumberResume);
        } else if (index < 0) {
            storage[countResumes++] = r;
        } else {
            System.out.println(RESUME_PRESENT + r.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndexResume(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println(RESUME_NOT_PRESENT + uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndexResume(uuid);
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

    public int size() {
        return countResumes;
    }

    public void update(Resume resume) {
        int index = getIndexResume(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println(RESUME_NOT_PRESENT + resume.getUuid());
        }
    }
}
