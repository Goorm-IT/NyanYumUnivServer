package com.nyanyumserver.nyanyumserver.mapper;

import com.nyanyumserver.nyanyumserver.VO.LikeInfo;
import com.nyanyumserver.nyanyumserver.VO.LikeSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface LikeMapper {
    int getLike(LikeSearchInfo likeSearchInfo);
    List<LikeInfo> getUserLikeList(LikeSearchInfo likeSearchInfo) throws SQLException;
    void setLike(LikeSearchInfo likeSearchInfo);
    void createLike(LikeSearchInfo likeSearchInfo);
    void setLikeCount(LikeSearchInfo likeSearchInfo);
}
