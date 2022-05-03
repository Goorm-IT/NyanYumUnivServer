package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.MenuService;
import com.nyanyumserver.nyanyumserver.VO.MenuSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("MenuService")
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public void getMenuList(MenuSearchInfo menuSearchInfo){
        try{
            menuSearchInfo.addMenuInfos(menuMapper.getMenuList(menuSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getValid(MenuSearchInfo menuSearchInfo){
        try{
            menuSearchInfo.addMenuInfos(menuMapper.getValid(menuSearchInfo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setMenu(MenuSearchInfo menuSearchInfo){
        try{
            menuMapper.setMenu(menuSearchInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
