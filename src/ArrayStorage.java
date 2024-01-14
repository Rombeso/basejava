import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int countResumes;

    void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    void save(Resume r) {
        storage[countResumes++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[countResumes - 1];
                storage[countResumes - 1] = null;
                countResumes--;
                break;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, countResumes);
    }

    int size() {
        return countResumes;
    }
}
