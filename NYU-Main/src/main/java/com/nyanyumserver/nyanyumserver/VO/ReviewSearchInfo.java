package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ReviewSearchInfo {
    private int reviewId;
    private String userAlias;
    private int storeId;
    private int menuId;
    private float score;
    private String content;
    private String imagePath;
    private boolean delete;
    private LocalDate registerDate;
    private ReviewInfo reviewInfo;

    private List<ReviewInfo> reviewInfos = new ArrayList<>();
    public void addReviewInfos(List<ReviewInfo> reviewInfos) {this.reviewInfos.addAll(reviewInfos);}
}

