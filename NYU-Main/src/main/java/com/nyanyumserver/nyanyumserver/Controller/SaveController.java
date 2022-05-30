package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Common.CommonConst;
import com.nyanyumserver.nyanyumserver.Common.CommonResponse;
import com.nyanyumserver.nyanyumserver.Service.SaveService;
import com.nyanyumserver.nyanyumserver.VO.SaveSearchInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@EnableAsync
@RestController
@EnableSwagger2
@RequiredArgsConstructor
@RequestMapping(value = "/nyu/save")
public class SaveController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SaveService saveService;

    @GetMapping("")
    @ApiResponse(code = 200, message = "{'show' = 1} : True(저장 누른 상태)\n{'show' = 0} : False(저장 안누른 상태)")
    @ApiOperation(value = "가게 저장 여부 확인", notes="로그인 상태에서만 가능합니다.\n가게 상세 페이지 상단 - 아이콘 색상 On(True) / Off(False)")
    public Object getSave(
            @ApiIgnore HttpSession session,
            @ApiParam(value = "가게 ID", required = true)
            @RequestParam(value = "storeId", required = true) int storeId
    ){
        try{
            if (logger.isDebugEnabled()) {
                logger.debug("START. getSave");
            }
            SaveSearchInfo saveSearchInfo = new SaveSearchInfo();

            String userAlias = (String) session.getAttribute("userAlias");
            if (userAlias.isEmpty()){
                throw new NullPointerException();
            }
            saveSearchInfo.setUserAlias(userAlias);
            saveSearchInfo.setStoreId(storeId);
            int flag = saveService.getSave(saveSearchInfo);
            if (flag == -1) flag = 0;

            Map<String, Object> result = new HashMap<>();
            result.put("userAlias", userAlias);
            result.put("storeId", storeId);
            result.put("show", flag);

            logger.debug("END. getSave");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    @ApiOperation(value = "사용자가 '저장' 누른 가게 리스트", notes = "로그인 상태에서만 가능합니다.\n가장 최근에 누른 순으로 정렬됩니다.")
    public Object getUserSaveList(
            @ApiIgnore HttpSession session
    ){
        if (logger.isDebugEnabled()) {
            logger.debug("START. getUserSaveList");
        }
        try{
            SaveSearchInfo saveSearchInfo = new SaveSearchInfo();
            String userAlias = (String) session.getAttribute("userAlias");

            if (userAlias.isEmpty()){
                throw new NullPointerException();
            }
            saveSearchInfo.setUserAlias(userAlias);
            saveService.getUserSaveList(saveSearchInfo);

            Map<String, Object> map = new HashMap<>();
            map.put("userSaveList", saveSearchInfo.getSaveInfos());

            logger.debug("END. getUserSaveList");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    @ApiOperation(value= "가게 저장 On/Off", notes = "로그인 상태에서만 가능합니다.")
    public Object setSave(
            @ApiIgnore HttpSession session,
            @ApiParam(value = "storId", required= true)
            @RequestParam(value = "storeId") Integer storeId
    ) {

        if (logger.isDebugEnabled()) {
            logger.debug("START. setSave");
        }
        try {
            SaveSearchInfo saveSearchInfo = new SaveSearchInfo();

            String userAlias = (String) session.getAttribute("userAlias");

            if (userAlias.isEmpty()){
                throw new NullPointerException();
            }
            saveSearchInfo.setUserAlias(userAlias);
            saveSearchInfo.setStoreId(storeId);

            int flag = saveService.getSave(saveSearchInfo);
            saveSearchInfo.setShow(flag);
            if (flag == -1) {
                saveService.createSave(saveSearchInfo);
                saveSearchInfo.setShow(0);
            }
            saveService.setSave(saveSearchInfo);
            saveService.setSaveCount(saveSearchInfo);
            logger.debug("END. setSave");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.OK), HttpStatus.OK);

        }catch (NullPointerException e){
            logger.debug("END. setSave");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("END. setSave");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

    }
}