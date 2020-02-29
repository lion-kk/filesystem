package com.itheima.controller;

import com.itheima.model.AccountPo;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;


@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/select")
    @ResponseBody
    public ModelAndView select(AccountPo accountPo) {
        ModelAndView modelAndView = new ModelAndView();
        if (accountService.select(accountPo)) {
            modelAndView.setViewName("success.html");
        } else {
            modelAndView.setViewName("error.html");
        }
        return modelAndView;

    }

    @RequestMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) throws Exception {

        String filePath = file.getOriginalFilename();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
}
