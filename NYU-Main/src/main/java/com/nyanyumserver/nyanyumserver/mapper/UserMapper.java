package com.nyanyumserver.nyanyumserver.mapper;

import com.nyanyumserver.nyanyumserver.VO.UserInfo;
import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserMapper {
    List<UserInfo> getLogin(UserSearchInfo userSearchInfo) throws SQLException;
    List<UserInfo> getRegister(UserSearchInfo userSearchInfo) throws SQLException;
    void getSecession(String uid) throws SQLException;
    boolean getUpdateNickName(UserSearchInfo userSearchInfo);
    boolean getUpdatePath(UserSearchInfo userSearchInfo);
    String getUid(UserSearchInfo userSearchInfo);
}
