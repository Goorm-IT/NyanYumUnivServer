package com.nyanyumserver.nyanyumserver.VO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class PostInfo {
    private String storeId;
    private String address;
    private  float score;
    private String commentId;
}
