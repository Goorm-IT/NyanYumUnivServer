package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MenuInfo implements Serializable {
    private Integer menuId;
    private String menuAlias;
    private Integer storeId;
    private Integer cost;
    private int choiceCount;
}
