package com.system.service.Impl;

import com.system.dao.AccountMapper;
import com.system.model.UserPo;
import com.system.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public String login(UserPo userPo) {
        UserPo select = accountMapper.query(userPo.getAccount(), userPo.getPassword());
        if (select!=null){
            String uuid = UUID.randomUUID().toString();
            accountMapper.update(uuid,select.getId());
            return uuid;
        }
        return null;
    }
}
