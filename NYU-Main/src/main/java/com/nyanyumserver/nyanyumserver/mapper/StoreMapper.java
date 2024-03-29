package com.nyanyumserver.nyanyumserver.mapper;


import com.nyanyumserver.nyanyumserver.VO.*;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface StoreMapper {
    List<StoreInfo> getStoreList(StoreSearchInfo storeSearchInfo) throws SQLException;
    List<StoreInfo> getStoreInfo(StoreSearchInfo storeSearchInfo) throws SQLException;
    List<StoreInfo> getMonthlyStore() throws SQLException;
    int getTotalCount() throws SQLException;
    List<StoreInfo> getSearchStore(StoreSearchInfo storeSearchInfo) throws SQLException;
    List<StoreInfo> getRegister(StoreSearchInfo storeSearchInfo) throws SQLException;
    String getStoreId(StoreSearchInfo storeSearchInfo);
    String getAddress(StoreSearchInfo storeSearchInfo);
    void setStorePath(StoreSearchInfo storeSearchInfo);
}
