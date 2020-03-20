package com.system.controller;

import com.system.instructure.aop.Anonymous;
import com.system.model.BaseResponse;
import com.system.model.FileUserPo;
import com.system.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
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
    public BaseResponse<Boolean> uploadfile(@RequestParam("file") MultipartFile File) throws IOException {
        if (File != null) {
            String filename = File.getOriginalFilename();
            return BaseResponse.success(fileService.SaveFileFromInputStream(File, filename));//保存到服务器的路径
        }
        return BaseResponse.success(false);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") int id) {
        fileService.delete(id);
    }

    @RequestMapping("/download")
    @ResponseBody
    @Anonymous
    public void downLoad(@RequestParam("id") int id ,HttpServletResponse httpServletResponse) {
        fileService.downLoad(id,httpServletResponse);
    }
}

