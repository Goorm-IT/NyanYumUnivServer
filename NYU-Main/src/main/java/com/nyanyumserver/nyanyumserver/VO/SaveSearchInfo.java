package com.nyanyumserver.nyanyumserver.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class SaveSearchInfo {
    private int saveId;
    private String userAlias;
    private int storeId;
    private int show;
    private LocalDate updateDate;
    private SaveInfo saveInfo;

    private List<SaveInfo> saveInfos = new ArrayList<>();
    public void addSaveInfos(List<SaveInfo> saveInfos) {this.saveInfos.addAll(saveInfos);}
}
