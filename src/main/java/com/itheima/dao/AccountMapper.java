package com.itheima.dao;

import com.itheima.model.AccountPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    AccountPo query(@Param("account") String account, @Param("password") String password);
}
