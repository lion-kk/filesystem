package com.itheima.service.Impl;

import com.itheima.dao.FileMapper;
import com.itheima.model.FileUserPo;
import com.itheima.model.UserPo;
import com.itheima.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;

    @Override
    public List<FileUserPo> getfiledata(String token) {
        UserPo queryuser = fileMapper.queryuser(token);
        if (queryuser.getAdminflag() == 1) {
            return fileMapper.queryall();
        } else if (queryuser.getAdminflag() == 0) {
            List<FileUserPo> query = fileMapper.query(token);
            return query;
        }
        return null;
    }

    @Override
    public void SaveFileFromInputStream(MultipartFile file, String savefile) throws IOException {
        InputStream is = file.getInputStream();
        String filePath =System.getProperty("user.dir") +  "/files";
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs(); //创建目录
        }
        FileOutputStream fs = new FileOutputStream(filePath + "/" + savefile);
        System.out.println("------------" + filePath + "/" + savefile);
        byte[] bys = new byte[1024 * 1024];
        int len = 0;
        while ((len = is.read(bys)) != -1) {
            fs.write(bys, 0, len);
            fs.flush();
        }
        fs.close();
        is.close();
    }
}
