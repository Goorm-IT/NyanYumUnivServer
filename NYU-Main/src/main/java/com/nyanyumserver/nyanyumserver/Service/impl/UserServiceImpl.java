package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.UserService;
import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;


@Service("UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void getLogin(UserSearchInfo userSearchInfo){
        try{
            userSearchInfo.addUserInfos(userMapper.getLogin(userSearchInfo));
        }catch (Exception e){
            logger.error(e.getMessage(), e);

        }
    }

    public void getRegister(UserSearchInfo userSearchInfo){
        try{
            userSearchInfo.addUserInfos(userMapper.getRegister(userSearchInfo));
        }catch (SQLException e){
            logger.error(e.getMessage(), e);
        }
    }

    public void getSecession(String uid){
        try{
            userMapper.getSecession(uid);
        }catch (SQLException e){
            logger.error(e.getMessage(), e);
        }
    }

    public boolean getUpdateUserAlias(UserSearchInfo userSearchInfo){
        return userMapper.getUpdateUserAlias(userSearchInfo);
    }


    public boolean getUpdatePath(UserSearchInfo userSearchInfo){
        return userMapper.getUpdatePath(userSearchInfo);
    }

    public String getUid(UserSearchInfo userSearchInfo){
        return userMapper.getUid(userSearchInfo);
    }
}
