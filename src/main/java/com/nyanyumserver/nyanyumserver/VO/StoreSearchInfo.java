package com.nyanyumserver.nyanyumserver.VO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StoreSearchInfo {
    private String storeId;
    private String address;
    private  float score;
    private String commentId;
}
