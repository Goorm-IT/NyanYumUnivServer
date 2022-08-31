package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.StoreService;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service("StoreService")
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreMapper storeMapper;

    @Override
    public void getStoreList(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getStoreList(storeSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getStoreInfo(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getStoreInfo(storeSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getMonthlyStore(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getMonthlyStore());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getSearchStore(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getSearchStore(storeSearchInfo));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getRegister(StoreSearchInfo storeSearchInfo){
        try{
            storeSearchInfo.addStoreInfos(storeMapper.getRegister(storeSearchInfo));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getStoreId(StoreSearchInfo storeSearchInfo){

        return storeMapper.getStoreId(storeSearchInfo);
    }

    @Override
    public String getAddress(StoreSearchInfo storeSearchInfo){
        return storeMapper.getAddress(storeSearchInfo);
    }

    @Override
    public void setStorePath(StoreSearchInfo storeSearchInfo){
        storeMapper.setStorePath(storeSearchInfo);
    }
}
