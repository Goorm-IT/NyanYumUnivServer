package com.nyanyumserver.nyanyumserver.VO;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserSearchInfo {
    @ApiModelProperty(example = "123")
    private String uid;
    private String nickName;
    private String userLevel;
    private String postId;
    private String path;
    private MultipartFile profileImg;
    private List<UserInfo> userInfos = new ArrayList<>();
    public void addUserInfos(List<UserInfo> userInfos) {
        this.userInfos.addAll(userInfos);
    }
}
