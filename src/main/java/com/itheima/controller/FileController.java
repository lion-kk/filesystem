package com.itheima.controller;

import com.itheima.model.BaseResponse;
import com.itheima.model.FileUserPo;
import com.itheima.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/getdata")
    @ResponseBody
    public BaseResponse<List<FileUserPo>> getfiledata(@RequestHeader String token) {
        List<FileUserPo> getfiledata = fileService.getfiledata(token);
        return BaseResponse.success(getfiledata);
    }

    @RequestMapping("/upload")
    public void uploadfile(@RequestParam("file") MultipartFile File) throws IOException {
        if (File != null) {
            String filename = File.getOriginalFilename();

            fileService.SaveFileFromInputStream(File, filename);//保存到服务器的路径
        }
    }
}

