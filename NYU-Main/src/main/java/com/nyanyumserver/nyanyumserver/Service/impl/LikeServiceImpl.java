package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.LikeService;
import com.nyanyumserver.nyanyumserver.VO.LikeSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("LikeService")
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeMapper likeMapper;

    @Override
    public int getLike(LikeSearchInfo likeSearchInfo) {
        return likeMapper.getLike(likeSearchInfo);
    }

    @Override
    public void getUserLikeList(LikeSearchInfo likeSearchInfo) {
        try {
            likeSearchInfo.addLikeInfos(likeMapper.getUserLikeList(likeSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLike(LikeSearchInfo likeSearchInfo) {
        likeMapper.setLike(likeSearchInfo);
    }

    @Override
    public void createLike(LikeSearchInfo likeSearchInfo) {
        likeMapper.createLike(likeSearchInfo);
    }

    @Override
    public void setLikeCount(LikeSearchInfo likeSearchInfo){
        likeMapper.setLikeCount(likeSearchInfo);
    }
}
