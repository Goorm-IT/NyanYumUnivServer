package com.nyanyumserver.nyanyumserver.Service;

import com.nyanyumserver.nyanyumserver.VO.SaveSearchInfo;

public interface SaveService {
    int getSave(SaveSearchInfo saveSearchInfo);
    void getUserSaveList(SaveSearchInfo saveSearchInfo);
    void setSave(SaveSearchInfo saveSearchInfo);
    void createSave(SaveSearchInfo saveSearchInfo);
    void setSaveCount(SaveSearchInfo saveSearchInfo);
}
