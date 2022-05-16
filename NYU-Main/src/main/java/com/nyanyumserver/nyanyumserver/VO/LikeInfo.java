package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class LikeInfo {
    private int likeId;
    private String userAlias;
    private int storeId;
    private int show;
    private LocalDate updateDate;
}
