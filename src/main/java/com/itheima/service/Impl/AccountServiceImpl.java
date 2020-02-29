package com.itheima.service.Impl;

import com.itheima.dao.AccountMapper;
import com.itheima.model.AccountPo;
import com.itheima.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public boolean select(AccountPo accountPo) {
        AccountPo select = accountMapper.query(accountPo.getAccount(), accountPo.getPassword());
        return select!=null;
    }
}
