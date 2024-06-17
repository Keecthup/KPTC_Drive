package dao;

import entity.FileUser;
import java.util.List;

public interface FileUserDao {

    FileUser queryById(Integer id);

    List<FileUser> queryAllByLimit(FileUser fileUser);

    long count(FileUser fileUser);

    int insert(FileUser fileUser);

    int insertBatch( List<FileUser> entities);

    int insertOrUpdateBatch( List<FileUser> entities);

    int update(FileUser fileUser);

    int deleteById(Integer id);

}

