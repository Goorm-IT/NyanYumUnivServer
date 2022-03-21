package com.nyanyumserver.nyanyumserver.mapper;


import com.nyanyumserver.nyanyumserver.VO.StoreInfo;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface storeMapper {
    List<StoreInfo> getStoreList() throws SQLException;
}
