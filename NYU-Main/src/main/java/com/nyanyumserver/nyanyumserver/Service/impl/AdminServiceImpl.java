package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.AdminService;
import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.AdminMapper;
import com.nyanyumserver.nyanyumserver.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void getAllUser(UserSearchInfo userSearchInfo){
        try{
            logger.debug("START.");
            userSearchInfo.addUserInfos(adminMapper.getAllUsers(userSearchInfo));

        } catch (SQLException e) {
            logger.debug(e.getMessage(), e);
        }

    }
}
