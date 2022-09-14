package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.SaveService;
import com.nyanyumserver.nyanyumserver.VO.SaveSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.SaveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("SaveService")
@RequiredArgsConstructor
public class SaveServiceImpl implements SaveService {

    private final SaveMapper saveMapper;

    @Override
    public int getSave(SaveSearchInfo saveSearchInfo) {
        return saveMapper.getSave(saveSearchInfo);
    }

    @Override
    public void getUserSaveList(SaveSearchInfo saveSearchInfo) {
        try {
            saveSearchInfo.addSaveInfos(saveMapper.getUserSaveList(saveSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSave(SaveSearchInfo saveSearchInfo) {
        saveMapper.setSave(saveSearchInfo);
    }

    @Override
    public void createSave(SaveSearchInfo saveSearchInfo) {
        saveMapper.createSave(saveSearchInfo);
    }
    @Override
    public void setSaveCount(SaveSearchInfo saveSearchInfo){
        saveMapper.setSaveCount(saveSearchInfo);
    }
}
