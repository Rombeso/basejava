package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private final Resume resume1 = new Resume(UUID_1);
    private final Resume resume2 = new Resume(UUID_2);
    private final Resume resume4 = new Resume(UUID_4);
    private final Resume resume3 = new Resume(UUID_3);

    protected AbstractArrayStorageTest(Storage storage) {
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
    void saveOverflow() throws Exception {
        storage.clear();
        for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("uuid_" + i));
            } catch (Exception e) {
                Assertions.fail("Unexpected exception was thrown");
            }
        }

        Assertions.assertEquals(STORAGE_LIMIT, storage.size());

        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume("uuid_" + (STORAGE_LIMIT + 1)));
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
        Assertions.assertArrayEquals(storage.getAll(), new Resume[0]);
    }

    @Test
    void getAll() throws Exception {
        Resume[] expected = new Resume[]{resume1, resume2, resume3};
        Assertions.assertArrayEquals(expected, storage.getAll());
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