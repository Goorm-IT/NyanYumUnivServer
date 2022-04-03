package com.nyanyumserver.nyanyumserver.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageInfo implements Serializable {
    private int totalCount;
    private int startPageNo;
    private int endPageNo;
    private int startIndex;
    private String option;

    public PageInfo(){
    }
    public PageInfo(int totalCount, int startPageNo, int endPageNo, int startIndex, String option){
        this.totalCount = totalCount;
        this.startPageNo = startPageNo;
        this.endPageNo = endPageNo;
        this.startIndex = startIndex;
        this.option = option;

    }

}