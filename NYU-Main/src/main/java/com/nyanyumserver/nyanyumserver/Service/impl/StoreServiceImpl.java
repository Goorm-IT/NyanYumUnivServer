package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.StoreService;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service("StoreService")
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreMapper storeMapper;

    public void getStoreList(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getStoreList(storeSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getStoreInfo(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getStoreInfo(storeSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMonthlyStore(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getMonthlyStore());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSearchStore(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getSearchStore(storeSearchInfo));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
