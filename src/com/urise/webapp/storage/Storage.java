package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.util.List;

public interface Storage {

    void clear();

    void save(Resume r);

    Object get(String uuid) throws IOException;

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();

    void update(Resume resume);
}
