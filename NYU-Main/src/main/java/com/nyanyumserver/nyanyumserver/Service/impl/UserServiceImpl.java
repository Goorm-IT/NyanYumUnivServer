package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.UserService;
import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;


@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void getLogin(UserSearchInfo userSearchInfo){
        try{
            userSearchInfo.addUserInfos(userMapper.getLogin(userSearchInfo));
        }catch (Exception e){
            logger.error(e.getMessage(), e);

        }
    }
    @Override
    public void getRegister(UserSearchInfo userSearchInfo){
        try{
            userSearchInfo.addUserInfos(userMapper.getRegister(userSearchInfo));
        }catch (SQLException e){
            logger.error(e.getMessage(), e);
        }
    }
    @Override
    public void getSecession(String uid){
        try{
            userMapper.getSecession(uid);
        }catch (SQLException e){
            logger.error(e.getMessage(), e);
        }
    }
    @Override
    public boolean getUpdateNickName(UserSearchInfo userSearchInfo){
        return userMapper.getUpdateNickName(userSearchInfo);
    }
    @Override
    public String getUid(UserSearchInfo userSearchInfo){
        return userMapper.getUid(userSearchInfo);
    }
}
