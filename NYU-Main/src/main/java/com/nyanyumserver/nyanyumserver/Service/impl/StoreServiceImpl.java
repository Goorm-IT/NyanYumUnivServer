package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.StoreService;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service("StoreService")
public class StoreServiceImpl implements StoreService {

    @Autowired
    com.nyanyumserver.nyanyumserver.mapper.storeMapper storeMapper;

    public void getStoreList(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getStoreList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
