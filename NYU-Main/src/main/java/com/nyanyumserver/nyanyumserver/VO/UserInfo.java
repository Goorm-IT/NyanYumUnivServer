package com.nyanyumserver.nyanyumserver.VO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfo {
    private String uid;
    private String userAlias;
    private String userLevel;
    private String imagePath;
    private LocalDate registerDate;
}
