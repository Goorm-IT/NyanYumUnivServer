package com.nyanyumserver.nyanyumserver.VO;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ReviewInfo {
    private int reviewId;
    private String userAlias;
    private int storeId;
    private int menuId;
    private float score;
    private String content;
    private String imagePath;
    private String propose;
    private LocalDate registerDate;
}
