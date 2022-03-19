package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class UserInfo {
    private String uid;
    private String nickName;
    private String userLevel;
    private String postId;
    private MultipartFile profileImg;
}
