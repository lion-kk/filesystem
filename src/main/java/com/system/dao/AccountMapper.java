package com.system.dao;

import com.system.model.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    UserPo query(@Param("account") String account, @Param("password") String password);
    int update(String token,long userId);
    boolean tokenquery(String token);
}
