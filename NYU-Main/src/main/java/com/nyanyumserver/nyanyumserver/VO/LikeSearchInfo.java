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
public class LikeSearchInfo {
    private int likeId;
    private String userAlias;
    private int storeId;
    private int show;
    private LocalDate updateDate;
    private LikeInfo likeInfo;

    private List<LikeInfo> likeInfos = new ArrayList<>();
    public void addLikeInfos(List<LikeInfo> likeInfos) {this.likeInfos.addAll(likeInfos);}
}
