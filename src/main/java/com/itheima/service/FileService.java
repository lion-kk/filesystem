package com.itheima.service;

import com.itheima.model.FileUserPo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<FileUserPo> getfiledata(String token);
    boolean SaveFileFromInputStream(MultipartFile file, String savefile) throws IOException;
}
