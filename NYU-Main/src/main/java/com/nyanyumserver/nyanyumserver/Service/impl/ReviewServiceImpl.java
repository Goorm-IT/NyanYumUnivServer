package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.ReviewService;
import com.nyanyumserver.nyanyumserver.VO.ReviewSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("ReviewService")
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    @Override
    public void getReview(ReviewSearchInfo reviewSearchInfo){
        try{
            reviewSearchInfo.addReviewInfos(reviewMapper.getReview(reviewSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getReviewList(ReviewSearchInfo reviewSearchInfo){
        try{
            reviewSearchInfo.addReviewInfos(reviewMapper.getReviewList(reviewSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getUserReviewList(ReviewSearchInfo reviewSearchInfo) {
        try {
            reviewSearchInfo.addReviewInfos(reviewMapper.getUserReviewList(reviewSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getReviewContent(ReviewSearchInfo reviewSearchInfo){
        try{
            reviewSearchInfo.addReviewInfos(reviewMapper.getReviewContent(reviewSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setReview(ReviewSearchInfo reviewSearchInfo){
        try{
            reviewMapper.setReview(reviewSearchInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setMenuCount(ReviewSearchInfo reviewSearchInfo){
        try{
            reviewMapper.setMenuCount(reviewSearchInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getReviewId(ReviewSearchInfo reviewSearchInfo){
        return reviewMapper.getReviewId(reviewSearchInfo);
    }

}