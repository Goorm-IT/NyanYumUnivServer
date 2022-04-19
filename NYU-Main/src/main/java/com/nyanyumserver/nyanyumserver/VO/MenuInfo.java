package com.nyanyumserver.nyanyumserver.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuInfo implements Serializable {
    private String menuId;
    private String menuAlias;
    private String cost;
    private String commentId;
    private float menuScore;
    private String path;
}
