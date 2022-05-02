package com.nyanyumserver.nyanyumserver.VO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class StoreInfo extends MenuInfo implements Serializable {
    private Integer storeId;
    private String address;
    private float score;
    private String commentId;
    private String category;
    private String path;
    private MenuInfo menuInfo;
}
