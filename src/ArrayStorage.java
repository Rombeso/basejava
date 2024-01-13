import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int lastFillElement = -1;

    void clear() {
        Arrays.fill(storage, null);
        lastFillElement = -1;
    }

    void save(Resume r) {
        lastFillElement++;
        storage[lastFillElement] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i <= lastFillElement; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;
        for (int i = 0; i <= lastFillElement; i++) {
            if (storage[i].toString().equals(uuid)) {
                index = i;
                break;
            }
        }
        for (int i = index; i < lastFillElement; i++) {
            storage[i] = storage[i + 1];
        }
        storage[lastFillElement] = null;
        lastFillElement--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return lastFillElement == -1
                ? new Resume[0]
                : Arrays.copyOfRange(storage, 0, lastFillElement + 1);
    }

    int size() {
        return lastFillElement + 1;
    }
}
