package com.system.controller;

import com.system.instructure.aop.Anonymous;
import com.system.model.UserPo;
import com.system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;




@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    @ResponseBody
    @Anonymous
    public ModelAndView login( UserPo userPo) {
        ModelAndView modelAndView = new ModelAndView();
        String login = accountService.login(userPo);
        if (login!=null) {
            modelAndView.addObject("token",login);
            modelAndView.addObject("account",userPo.getAccount());
            modelAndView.setViewName("success.html");
        } else {
            modelAndView.setViewName("error.html");
        }
        return modelAndView;

    }

    @RequestMapping("/index")
    @Anonymous
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
}
