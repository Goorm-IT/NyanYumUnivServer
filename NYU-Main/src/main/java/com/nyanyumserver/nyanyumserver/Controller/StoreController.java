package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Service.StoreService;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


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
    public Object getStoreList(
            @ApiParam(value = "pageNo", required = true) @RequestParam(value = "pageNo", required = true) int pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize){

        if (logger.isDebugEnabled()){
            logger.debug("START. getStoreList");
        }
        try{
            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();

            storeService.getStoreList(storeSearchInfo);

            if (logger.isDebugEnabled()){
                logger.debug("END. getStoreList");
            }

            return storeSearchInfo.getStoreInfos();
        }catch (Exception e){
            logger.error("ERROR, getStoreList");
            return new ResponseEntity<>("불러올 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addStore")
    @ApiOperation(value= "가게 추가")
    public Object addStore(){

        if (logger.isDebugEnabled()) {
            logger.debug("START. addStore");
        }
        try{

            logger.debug("END. addStore");
            return null;
        }catch (Exception e){
            if (logger.isDebugEnabled()) {
                logger.error("ERROR. addStore");
            }
            return new ResponseEntity<>("이미 존재하는 가게 입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/storeInfo")
    @ApiOperation(value = "가게 정보")
    public Object getStoreInfo(
            @ApiParam(value = "가게 ID", required = true) @RequestParam(value = "storeId", required = true) String storeId
    ){
        try{
            if (logger.isDebugEnabled()) {
                logger.debug("START. storeInfo");
            }

            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();
            storeSearchInfo.setStoreId(storeId);

            storeService.getStoreInfo(storeSearchInfo);

            logger.debug("END. storeInfo");

            return new ResponseEntity<>(storeSearchInfo.getStoreInfos(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("가게 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/monthlyStore")
    @ApiOperation(value = "이달의 가게")
    public Object getMonthlyStore(){
        try{
            if (logger.isDebugEnabled()) {
                logger.debug("START. monthlyStore");
            }

            logger.debug("END. monthlyStore");

            return "qwe";
        }catch (Exception e){
            return "qwe";
        }

    }
}
