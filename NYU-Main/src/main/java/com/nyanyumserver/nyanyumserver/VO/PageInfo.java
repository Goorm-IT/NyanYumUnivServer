package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PageInfo implements Serializable {
    private int totalCount;
    private int startPageNo;
    private int endPageNo;
    private int startIndex;
    private String order;

    public PageInfo(){
    }
    public PageInfo(int totalCount, int startPageNo, int endPageNo, int startIndex, String order){
        this.totalCount = totalCount;
        this.startPageNo = startPageNo;
        this.endPageNo = endPageNo;
        this.startIndex = startIndex;
        this.order = order;

    }

}
