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
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume uuid_4 = new Resume(UUID_4);
    private static final Resume uuid_3 = new Resume(UUID_3);
    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
    storage.clear();
    storage.save(new Resume(UUID_1));
    storage.save(new Resume(UUID_2));
    storage.save(uuid_3);
    }
    @Test
    void size() throws Exception{
      Assertions.assertEquals(3, storage.size());
    }

    @Test
    void get() throws Exception {
        Assertions.assertTrue(uuid_3.equals(storage.get(UUID_3)));
    }

    @Test
    void getNotExist() throws Exception {
       Assertions.assertThrows(NotExistStorageException.class, () -> {
               storage.get("dummy");
       });
    }

    @Test
    void save() throws Exception{
        storage.save(uuid_4);
        Assertions.assertEquals(4, storage.size());
    }
    @Test
    void saveExist() throws Exception {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(uuid_3);
        });
    }
    @Test
    void saveOverflow() throws Exception{
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
    void delete() throws Exception{
        int sizeStorage = storage.size();
        storage.delete(uuid_3.getUuid());
        Assertions.assertEquals(sizeStorage - 1, storage.size());
    }
    @Test
    void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
    }

    @Test
    void clear() throws Exception{
        int sizeStorage = storage.size();
        storage.clear();
        Assertions.assertTrue(sizeStorage > 0);
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void getAll() throws Exception{
        Assertions.assertArrayEquals(storage.getAll(), storage.getAll());
        Assertions.assertEquals(storage.size(), storage.getAll().length);
        Resume[] allResume = storage.getAll();
        for (int i = 0; i < storage.size(); i++) {
            String uuid = allResume[i].getUuid();
            Assertions.assertTrue(storage.get(uuid).equals(allResume[i]));
        }
    }

    @Test
    void update() throws Exception{
        storage.update(uuid_3);
        Assertions.assertTrue(uuid_3.equals(storage.get(uuid_3.getUuid())));
    }

    @Test
    void updateNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(uuid_4);
        });
    }
}