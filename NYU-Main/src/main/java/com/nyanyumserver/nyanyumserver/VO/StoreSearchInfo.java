package com.nyanyumserver.nyanyumserver.VO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data

public class StoreSearchInfo {
    private String storeId;
    private String address;
    private  float score;
    private String commentId;
    private List<StoreInfo> storeInfos = new ArrayList<>();
    public void addStoreInfos(List<StoreInfo> storeInfos) {
        this.storeInfos.addAll(storeInfos);
    }
}
