package com.example.drivedaotest.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private Path userLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {

        if(properties.getLocation().trim().isEmpty()){
            throw new StorageException("File upload location can not be Empty.");
        }

        this.rootLocation = Paths.get(properties.getLocation());
        this.userLocation = rootLocation;
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.userLocation.resolve(
                            Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.userLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.userLocation, 1)
                    .filter(path -> !path.equals(this.userLocation))
                    .map(this.userLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return userLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(userLocation.toFile());
    }

    @Override
    public boolean delete(String filename) {
        try {
            Path file = userLocation.resolve(filename);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        
    }

    @Override
    public boolean rename(String filename, String newFilename){
        try {
            Path source = userLocation.resolve(filename);
            Path target = source.resolveSibling(newFilename);
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            return Files.exists(target);
        } catch (IOException e) {
            throw new StorageException("Failed to rename file", e);
        }
    }

    @Override
    public void createFile(String filename){
        try {
            Path newFile = userLocation.resolve(filename);
            Files.createFile(newFile);
        } catch (IOException e) {
            throw new StorageException("Failed to create file", e);
        }
    }

    @Override
    public void createDir(String folder){
        try {
            Path newFolder = userLocation.resolve(folder);
            Files.createDirectory(newFolder);
        }
        catch (IOException e) {
            throw new StorageException("Failed to create new folder", e);
        }
    }

    @Override
    public Path changeDir(String folder) {
        Path newLocation = userLocation.resolve(folder).normalize().toAbsolutePath();
        if (!Files.isDirectory(newLocation)) {
            throw new StorageException("Directory does not exist: " + folder);
        }
        userLocation = newLocation;
        return newLocation.getFileName();
    }

    @Override
    public Path backDir() {
        if (!userLocation.toAbsolutePath().normalize().equals(rootLocation.toAbsolutePath().normalize())) {
            userLocation = userLocation.getParent();
        }
        return userLocation.getFileName();
    }

    @Override
    public void moveToDir(String filename, String folder) {
        try {
            Path file = userLocation.resolve(filename);
            Path folderToMove = userLocation.resolve(folder);
            if (!Files.isDirectory(folderToMove)) {
                throw new StorageException("Directory does not exist: " + folder);
            }
            Files.move(file, folderToMove.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageException("Failed to move file", e);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(userLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize com.example.drivedaotest.storage", e);
        }
    }
}
