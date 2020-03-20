package com.system.service;

import com.system.model.FileUserPo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface FileService {
    List<FileUserPo> getfiledata(String token);
    boolean SaveFileFromInputStream(MultipartFile file, String savefile) throws IOException;
    void delete(int id);
    void downLoad(int id, HttpServletResponse httpServletResponse);
}
