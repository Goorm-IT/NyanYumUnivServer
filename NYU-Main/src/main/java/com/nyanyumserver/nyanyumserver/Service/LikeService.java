package com.nyanyumserver.nyanyumserver.Service;

import com.nyanyumserver.nyanyumserver.VO.LikeSearchInfo;

public interface LikeService {
    int getLike(LikeSearchInfo likeSearchInfo);
    void getUserLikeList(LikeSearchInfo likeSearchInfo);
    void setLike(LikeSearchInfo likeSearchInfo);
    void createLike(LikeSearchInfo likeSearchInfo);
    void setLikeCount(LikeSearchInfo likeSearchInfo);
}
