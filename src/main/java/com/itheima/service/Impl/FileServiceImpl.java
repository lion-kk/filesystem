package com.itheima.service.Impl;

import com.itheima.dao.FileMapper;
import com.itheima.model.FilePo;
import com.itheima.model.FileUserPo;
import com.itheima.model.UserPo;
import com.itheima.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;

    @Override
    public List<FileUserPo> getfiledata(String token) {
        List<FileUserPo> query = fileMapper.query(token);
        return query;
    }
}
