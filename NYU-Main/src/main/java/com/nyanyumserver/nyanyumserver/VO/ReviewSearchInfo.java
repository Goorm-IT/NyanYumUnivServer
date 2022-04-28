package com.nyanyumserver.nyanyumserver.VO;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewSearchInfo {
    private int reviewId;
    private String userAlias;
    private int storeId;
    private int menuId;
    private float score;
    private String content;
    private String imagePath;
    private String propose;
    private LocalDate registerDate;
    private ReviewInfo reviewInfo;

    private List<ReviewInfo> reviewInfos = new ArrayList<>();
    public void addReviewInfos(List<ReviewInfo> reviewInfos) {this.reviewInfos.addAll(reviewInfos);}
}

