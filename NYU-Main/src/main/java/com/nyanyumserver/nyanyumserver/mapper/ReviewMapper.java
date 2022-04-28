package com.nyanyumserver.nyanyumserver.mapper;

import com.nyanyumserver.nyanyumserver.VO.ReviewInfo;
import com.nyanyumserver.nyanyumserver.VO.ReviewSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface ReviewMapper {
    List<ReviewInfo> getReview(ReviewSearchInfo reviewSearchInfo) throws SQLException;
    List<ReviewInfo> getUserReviewList(ReviewSearchInfo reviewSearchInfo) throws SQLException;
    List<ReviewInfo> getReviewList (ReviewSearchInfo reviewSearchInfo) throws SQLException;
    List<ReviewInfo> getReviewContent (ReviewSearchInfo reviewSearchInfo) throws SQLException;
    void setReview(ReviewSearchInfo reviewSearchInfo) throws SQLException;
    Integer getReviewId(ReviewSearchInfo reviewSearchInfo);
}
