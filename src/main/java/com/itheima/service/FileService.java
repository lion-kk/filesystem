package com.itheima.service;

import com.itheima.model.FilePo;
import com.itheima.model.FileUserPo;

import java.util.List;

public interface FileService {
    List<FileUserPo> getfiledata(String token);
}
