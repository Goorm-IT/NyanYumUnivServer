package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MenuInfo implements Serializable {
    private String menuId;
    private String menuAlias;
    private String cost;
    private String commentId;
    private float menuScore;
    private String path;
}
