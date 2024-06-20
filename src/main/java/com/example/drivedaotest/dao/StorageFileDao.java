package com.example.drivedaotest.dao;

import com.example.drivedaotest.entity.StorageFile;

import java.util.List;


public interface StorageFileDao {

    StorageFile queryById(Integer id);

    List<StorageFile> queryAllByLimit(StorageFile storageFile);

    long count(StorageFile storageFile);

    int insert(StorageFile storageFile);

    int insertBatch( List<StorageFile> entities);

    int insertOrUpdateBatch( List<StorageFile> entities);

    int update(StorageFile storageFile);

    int deleteById(Integer id);

}

