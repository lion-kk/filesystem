package com.system.service.Impl;

import com.system.dao.FileMapper;
import com.system.model.FileUserPo;
import com.system.model.UserPo;
import com.system.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;
    @Autowired
    HttpServletRequest httpServletRequest;


    @Override
    public List<FileUserPo> getfiledata(String token) {
        UserPo queryuser = fileMapper.queryUser(token);
        if (queryuser.getAdminflag() == 1) {
            return fileMapper.queryAll();
        } else if (queryuser.getAdminflag() == 0) {
            List<FileUserPo> query = fileMapper.query(token);
            return query;
        }
        return null;
    }

    @Override
    public boolean SaveFileFromInputStream(MultipartFile file, String savefile) throws IOException {
        InputStream is = null;
        FileOutputStream fs = null;
        try {
            is = file.getInputStream();
            String filePath = System.getProperty("user.dir") + "/files";
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs(); //创建目录
            }
            fs = new FileOutputStream(filePath + "/" + savefile);
            System.out.println("------------" + filePath + "/" + savefile);
            byte[] bys = new byte[1024 * 1024];
            int len = 0;
            while ((len = is.read(bys)) != -1) {
                fs.write(bys, 0, len);
                fs.flush();
            }
            FileUserPo fileUserPo = new FileUserPo();
            fileUserPo.setSize(file.getSize());
            fileUserPo.setName(file.getOriginalFilename());
            fileUserPo.setSuffix(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            fileUserPo.setPath("/files");
            fileUserPo.setUploaddate(new Date());
            fileUserPo.setType(1);
            String token = httpServletRequest.getHeader("token");
            fileUserPo.setCreator_id(fileMapper.queryUserId(token));
            fileMapper.insert(fileUserPo);
            return true;
        } catch (IOException e) {
            throw e;
        } finally {
            fs.close();
            is.close();
        }
    }

    @Override
    public void delete(int id) {
        fileMapper.delete(id);
    }

    @Override
    public void downLoad(int id, HttpServletResponse httpServletResponse) {
        String fileName = fileMapper.downLoad(id);
        String splicingFileName = "/"+fileName;
        String path = System.getProperty("user.dir") + "/files";
        File file = new File(path+splicingFileName);
        if (file.exists()) {
            httpServletResponse.setContentType("application/force-download");
            try {
                httpServletResponse.setHeader( "Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024 * 1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = httpServletResponse.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                outputStream.flush();
                return ;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
