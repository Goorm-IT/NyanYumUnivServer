package com.nyanyumserver.nyanyumserver.Service;

import com.nyanyumserver.nyanyumserver.VO.MenuSearchInfo;

public interface MenuService {
    void getMenuList(MenuSearchInfo menuSearchInfo);
    void getValid(MenuSearchInfo menuSearchInfo);
    void setMenu(MenuSearchInfo menuSearchInfo);
}
