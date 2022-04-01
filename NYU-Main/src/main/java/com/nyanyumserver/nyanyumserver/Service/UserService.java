package com.nyanyumserver.nyanyumserver.Service;

import com.nyanyumserver.nyanyumserver.VO.UserSearchInfo;

public interface UserService {
    void getLogin(UserSearchInfo userSearchInfo);
    void getRegister(UserSearchInfo userSearchInfo);
    void getSecession(String uid);
    boolean getUpdateNickName(UserSearchInfo userSearchInfo);
    boolean getUpdatePath(UserSearchInfo UserSearchInfo);
    String getUid(UserSearchInfo userSearchInfo);
}
