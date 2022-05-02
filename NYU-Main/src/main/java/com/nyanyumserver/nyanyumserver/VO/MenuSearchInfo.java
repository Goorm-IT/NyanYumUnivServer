package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MenuSearchInfo implements Serializable {
    private Integer menuId;
    private String menuAlias;
    private Integer storeId;
    private Integer cost;
    private int choiceCount;

    private List<MenuInfo> menuInfos = new ArrayList<>();
    public void addMenuInfos(List<MenuInfo> menuInfos) {this.menuInfos.addAll(menuInfos);}
}
