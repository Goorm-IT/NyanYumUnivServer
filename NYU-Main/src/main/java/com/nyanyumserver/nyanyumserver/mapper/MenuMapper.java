package com.nyanyumserver.nyanyumserver.mapper;

import com.nyanyumserver.nyanyumserver.VO.MenuInfo;
import com.nyanyumserver.nyanyumserver.VO.MenuSearchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface MenuMapper {
    List<MenuInfo> getMenuList (MenuSearchInfo menuSearchInfo) throws SQLException;
    List<MenuInfo> getValid (MenuSearchInfo menuSearchInfo) throws SQLException;
    void setMenu(MenuSearchInfo menuSearchInfo) throws SQLException;
}
