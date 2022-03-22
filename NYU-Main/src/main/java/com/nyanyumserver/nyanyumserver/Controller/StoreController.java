package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Service.StoreService;
import com.nyanyumserver.nyanyumserver.VO.StoreInfo;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;
import com.nyanyumserver.nyanyumserver.VO.UserInfo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@EnableAsync
@RestController
@EnableSwagger2
@RequiredArgsConstructor
public class StoreController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StoreService storeService;

    @GetMapping("/storeList")
    @ApiOperation(value = "가게 리스트")
    public Object getStoreList(){

        try{
            logger.debug("START.");
            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();

            storeService.getStoreList(storeSearchInfo);


            logger.debug("END");
            return storeSearchInfo.getStoreInfos();
        }catch (Exception e){
            logger.debug("ERROR, StoreList");

            return new ResponseEntity<>("불러올 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addStore")
    @ApiOperation(value= "가게 추가")
    public Object addStore(){
        try{
            logger.debug("START.");

            return null;
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/storeInfo")
    @ApiOperation(value = "가게 정보")
    public Object getStoreInfo(){
        try{
            logger.debug("START.");

            logger.debug("END.");

            return null;
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/monthlyStore")
    @ApiOperation(value = "이달의 가게")
    public Object getMonthlyStore(){
        try{
            logger.debug("START.");

            logger.debug("END.");

            return "qwe";
        }catch (Exception e){
            return "qwe";
        }

    }
}
