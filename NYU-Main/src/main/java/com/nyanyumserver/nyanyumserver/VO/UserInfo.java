package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserInfo {
    private String uid;
    private String userAlias;
    private String userLevel;
    private String imagePath;
    private LocalDate registerDate;
}
