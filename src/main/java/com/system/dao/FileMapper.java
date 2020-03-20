package com.system.dao;

import com.system.model.FileUserPo;
import com.system.model.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    List<FileUserPo> query(String token);
    UserPo queryUser(String token);
    List<FileUserPo> queryAll();
    void insert(FileUserPo fileUserPo);
    int queryUserId(String token);
    void delete(int id);
    String downLoad(int id);
}
