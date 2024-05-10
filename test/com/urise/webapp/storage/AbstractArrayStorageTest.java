package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void saveOverflow() throws Exception {
        storage.clear();
        for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("fullName" + i));
            } catch (Exception e) {
                Assertions.fail("Unexpected exception was thrown");
            }
        }

        Assertions.assertEquals(STORAGE_LIMIT, storage.size());

        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume("uuid_" + (STORAGE_LIMIT + 1), "Overflow"));
        });
    }
}