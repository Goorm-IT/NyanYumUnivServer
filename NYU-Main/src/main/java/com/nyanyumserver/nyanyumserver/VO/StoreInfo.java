package com.nyanyumserver.nyanyumserver.VO;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class StoreInfo extends MenuInfo implements Serializable {
    private String storeId;
    private String address;
    private float score;
    private String commentId;
    private String category;
    private MenuInfo menuInfo;
    private String path;
}
