package com.nyanyumserver.nyanyumserver.mapper;

import com.nyanyumserver.nyanyumserver.VO.SaveInfo;
import com.nyanyumserver.nyanyumserver.VO.SaveSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface SaveMapper {
    int getSave(SaveSearchInfo saveSearchInfo);
    List<SaveInfo> getUserSaveList(SaveSearchInfo saveSearchInfo) throws SQLException;
    void setSave(SaveSearchInfo saveSearchInfo);
    void createSave(SaveSearchInfo saveSearchInfo);
    void setSaveCount(SaveSearchInfo saveSearchInfo);
}
