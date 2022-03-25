package com.nyanyumserver.nyanyumserver.mapper;

import com.nyanyumserver.nyanyumserver.VO.UserInfo;
import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface AdminMapper {
    List<UserInfo> getAllUsers(UserSearchInfo userSearchInfo) throws SQLException;
}
