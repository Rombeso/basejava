package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
            File[] listFiles = directory.listFiles();
            List<Resume> list = new ArrayList<>();
            for (File file : listFiles) {
                try {
                    list.add(doRead(file));
                } catch (IOException e) {
                    throw new StorageException("Error read file", file.getName(), e);
                }
            }
        }
        return null;
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
            File[] listFiles = directory.listFiles();
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
        file.delete();
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

    protected abstract void doWrite(Resume r, File file) throws IOException;
    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected int getSize() {
        File[] listFiles = directory.listFiles();
        return listFiles.length;
    }
}
