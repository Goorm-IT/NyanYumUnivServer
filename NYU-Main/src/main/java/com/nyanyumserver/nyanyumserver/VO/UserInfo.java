package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.FileSystemResource;

@Getter
@Setter
public class UserInfo {
    private String uid;
    private String nickName;
    private String userLevel;
    private String postId;
    private String path;
    private String profileImg;
}
