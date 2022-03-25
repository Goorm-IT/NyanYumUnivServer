package com.nyanyumserver.nyanyumserver.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageInfo implements Serializable {
    private int totalCount;
    private int pageNo;
    private int pageSize;
    private int startIndex;

    public PageInfo(){

    }
    public PageInfo(int totalCount, int pageNo, int pageSize, int startIndex){
        this.totalCount = totalCount;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.startIndex = startIndex;
    }

    private List<StoreInfo> storeInfos = new ArrayList<>();
    public void addStoreInfos(List<StoreInfo> storeInfos) {
        this.storeInfos.addAll(storeInfos);
    }


}
