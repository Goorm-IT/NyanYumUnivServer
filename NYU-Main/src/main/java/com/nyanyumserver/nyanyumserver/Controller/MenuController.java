package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Common.CommonConst;
import com.nyanyumserver.nyanyumserver.Common.CommonResponse;
import com.nyanyumserver.nyanyumserver.Service.MenuService;
import com.nyanyumserver.nyanyumserver.VO.MenuSearchInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@RestController
@EnableSwagger2
@RequiredArgsConstructor
@RequestMapping(value = "/nyu/menu")
public class MenuController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuService menuService;

    @GetMapping("/store")
    @ApiOperation(value = "가게별 메뉴 로드")
    public Object getMenuList(
            @ApiParam(value = "가게 ID", required = true)
            @RequestParam(value = "storeId", required = true) Integer storeId
    ){
        if (logger.isDebugEnabled()) {
            logger.debug("START. getMenuList");
        }
        try{
            MenuSearchInfo MenuSearchInfo = new MenuSearchInfo();
            MenuSearchInfo.setStoreId(storeId);
            menuService.getMenuList(MenuSearchInfo);

            // 메뉴 존재 X
            if (MenuSearchInfo.getMenuInfos().size() == 0)
                return new ResponseEntity<>(CommonResponse.of(CommonConst.NO_CONTENT), HttpStatus.NO_CONTENT);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("MenuList", MenuSearchInfo.getMenuInfos());

            logger.debug("END. getMenuList");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    @ApiOperation(value= "새 메뉴 추가")
    public Object setMenu(
            @ApiParam(value = "메뉴이름", required= true) @RequestParam(value = "menuAlias") String menuAlias,
            @ApiParam(value = "가게 ID", required= true) @RequestParam(value = "storeId") Integer storeId,
            @ApiParam(value = "가격", required= true) @RequestParam(value = "cost") Integer cost,
            @ApiIgnore HttpSession session, HttpServletResponse response
    ) {

        if (logger.isDebugEnabled()) {
            logger.debug("START. setMenu");
        }

        MenuSearchInfo menuSearchInfo = new MenuSearchInfo();

        try {

            if (menuAlias.isEmpty()) {
                throw new NullPointerException();
            }
            menuSearchInfo.setMenuAlias(menuAlias);
            menuSearchInfo.setStoreId(storeId);
            menuSearchInfo.setCost(cost);
            menuService.getValid(menuSearchInfo);

            if (menuSearchInfo.getMenuInfos().size() > 0)
                throw new SQLException();

            menuService.setMenu(menuSearchInfo);
            logger.debug("END. setMenu");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.OK), HttpStatus.OK);
        }catch (SQLException e){
            logger.debug("END. setMenu");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("END. setMenu");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }
}
