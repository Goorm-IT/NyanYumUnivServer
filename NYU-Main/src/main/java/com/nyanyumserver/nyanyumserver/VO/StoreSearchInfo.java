package com.nyanyumserver.nyanyumserver.VO;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data

public class StoreSearchInfo extends PageInfo implements Serializable {
    private String storeId;
    private String address;
    private  float score;
    private String commentId;
    private String category;
    private String path;

    private MenuInfo menuInfo;

    private List<StoreInfo> storeInfos = new ArrayList<>();
    public void addStoreInfos(List<StoreInfo> storeInfos) {
        this.storeInfos.addAll(storeInfos);
    }
}
