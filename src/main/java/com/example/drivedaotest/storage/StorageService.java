package com.example.drivedaotest.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    boolean delete(String filename);

    boolean rename(String filename, String newFilename);

    void createFile(String filename);

    Path changeDir(String folder);

    Path backDir();

    void createDir(String folder);

    void moveToDir(String filename, String folder);
}
