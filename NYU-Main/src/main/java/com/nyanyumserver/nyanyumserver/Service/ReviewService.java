package com.nyanyumserver.nyanyumserver.Service;

import com.nyanyumserver.nyanyumserver.VO.ReviewSearchInfo;

public interface ReviewService {
    void getReview(ReviewSearchInfo reviewSearchInfo);
    void getReviewList(ReviewSearchInfo reviewSearchInfo);
    void getUserReviewList(ReviewSearchInfo reviewSearchInfo);
    void getReviewContent(ReviewSearchInfo reviewSearchInfo);
    void setReview(ReviewSearchInfo reviewSearchInfo);
    void setMenuCount(ReviewSearchInfo reviewSearchInfo);
    void deleteReview(ReviewSearchInfo reviewSearchInfo);
    Integer getReviewId(ReviewSearchInfo reviewSearchInfo);
}