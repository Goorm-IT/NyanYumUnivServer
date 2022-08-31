package com.nyanyumserver.nyanyumserver.Service;

import com.nyanyumserver.nyanyumserver.VO.StoreInfo;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;

public interface StoreService {
    void getStoreList(StoreSearchInfo storeSearchInfo);
    void getStoreInfo(StoreSearchInfo storeSearchInfo);
    void getMonthlyStore(StoreSearchInfo storeSearchInfo);
    void getSearchStore(StoreSearchInfo storeSearchInfo);
    void getRegister(StoreSearchInfo storeSearchInfo);
    String getStoreId(StoreSearchInfo storeSearchInfo);
    String getAddress(StoreSearchInfo storeSearchInfo);
    void setStorePath(StoreSearchInfo storeSearchInfo);
}
