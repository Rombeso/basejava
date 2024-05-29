package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable|writable");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        if (getSize() != 0) {
            File[] listFiles = getCheckedListFiles();

            List<Resume> list = new ArrayList<>();
            for (File file : listFiles) {
                list.add(doGet(file));
            }
            return list;
        }
        return Collections.emptyList();
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Error read file", file.getName(), e);
        }
    }

    @Override
    protected void clearAllElements() {
        if (getSize() != 0) {
            File[] listFiles = getCheckedListFiles();
            for (File file : listFiles) {
                doDelete(file);
            }
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doDelete(File file) {
        boolean delete = file.delete();
        if (!delete) {
            throw new StorageException("Error delete file", file.getName());
        }
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected int getSize() {
        File[] listFiles = getCheckedListFiles();
        return listFiles.length;
    }

    protected File[] getCheckedListFiles() {
        try {
            return directory.listFiles();
        } catch (StorageException e) {
            throw new StorageException("IO error", directory.getPath(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
