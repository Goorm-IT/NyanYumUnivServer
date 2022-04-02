package com.nyanyumserver.nyanyumserver.VO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfo {
    private String uid;
    private String nickName;
    private String userLevel;
    private String postId;
    private String path;
    private String profileImg;
    private LocalDate registerDate;
}
