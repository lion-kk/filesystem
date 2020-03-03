package com.itheima.dao;

import com.itheima.model.FileUserPo;
import com.itheima.model.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    List<FileUserPo> query(String token);
    UserPo queryuser(String token);
    List<FileUserPo> queryall();
}
