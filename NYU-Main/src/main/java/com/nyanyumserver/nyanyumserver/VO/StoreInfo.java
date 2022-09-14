package com.nyanyumserver.nyanyumserver.VO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class StoreInfo extends PageInfo implements Serializable {
    private Integer storeId;
    private String storeAlias;
    private String address;
    private String category;
    private String mapX;
    private String mapY;
    private float score;
    private Integer scoreCount;
    private Integer likeCount;
    private Integer saveCount;
    private String commentId;
    private String imagePath;
    private LocalDate registerDate;
    private LocalDate updateDate;
}
