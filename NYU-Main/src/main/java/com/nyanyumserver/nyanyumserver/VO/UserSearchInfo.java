package com.nyanyumserver.nyanyumserver.VO;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UserSearchInfo {
    @ApiModelProperty(example = "123")
    private String uid;
    private String userAlias;
    private String userLevel;
    private String imagePath;
    private LocalDate registerDate;
    private List<UserInfo> userInfos = new ArrayList<>();
    public void addUserInfos(List<UserInfo> userInfos) {
        this.userInfos.addAll(userInfos);
    }
}
