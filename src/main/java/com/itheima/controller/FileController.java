package com.itheima.controller;

import com.itheima.model.BaseResponse;
import com.itheima.model.FileUserPo;
import com.itheima.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @RequestMapping("/getfiledata")
    @ResponseBody
    public BaseResponse<List<FileUserPo>> getfiledata(@RequestHeader String token) {
        List<FileUserPo> getfiledata = fileService.getfiledata(token);
        return BaseResponse.success(getfiledata);
    }
}
