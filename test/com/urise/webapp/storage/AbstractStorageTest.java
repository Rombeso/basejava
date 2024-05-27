package com.urise.webapp.storage;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

abstract class AbstractStorageTest {
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private final Resume resume1 = ResumeTestData.createResume(UUID_1, "Name1");
    private final Resume resume2 = ResumeTestData.createResume(UUID_2, "Name2");
    private final Resume resume4 = ResumeTestData.createResume(UUID_4, "Name3");
    private final Resume resume3 = ResumeTestData.createResume(UUID_3, "Name4");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    void size() throws Exception {
        assertSize(3);
    }

    private void assertSize(int size) throws Exception {
        Assertions.assertEquals(size, storage.size());
    }

    @Test
    void get() throws Exception {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    private void assertGet(Resume resume) throws Exception {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    void save() throws Exception {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(4);
    }

    @Test
    void saveExist() throws Exception {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(resume3);
        });
    }

    @Test
    void delete() throws Exception {
        assertSize(3);
        storage.delete(resume3.getUuid());
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(resume3.getUuid());
        });
    }

    @Test
    void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
    }

    @Test
    void clear() throws Exception {
        assertSize(3);
        storage.clear();
        assertSize(0);
        Assertions.assertArrayEquals(storage.getAllSorted().toArray(new Resume[0]), new Resume[0]);
    }

    @Test
    void getAllSorted() throws Exception {
        Resume[] expected = new Resume[]{resume1, resume2, resume3};
        Resume[] actual = storage.getAllSorted().toArray(new Resume[0]);
        Arrays.sort(expected);
        Arrays.sort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void update() throws Exception {
        storage.update(resume3);
        Assertions.assertSame(resume3, storage.get(resume3.getUuid()));
    }

    @Test
    void updateNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(resume4);
        });
    }
}