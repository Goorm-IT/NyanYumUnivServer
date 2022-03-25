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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EnableAsync
@RestController
@EnableSwagger2
@RequiredArgsConstructor
public class StoreController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StoreService storeService;

    @GetMapping(value = "/storeList")
    @ApiOperation(value = "가게 리스트")
    public Object getStoreList(
            @ApiParam(value = "pageNo", required = true) @RequestParam(value = "pageNo", required = true) int pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam(value = "pageSize", required = true) int pageSize,
            @ApiParam(value = "storeId", required = false) @RequestParam(value = "storeId", required = false) String storeId,
            @ApiParam(value = "category", required = false) @RequestParam(value = "category", required = false) String category,
            @ApiParam(value = "option" , required = false) @RequestParam(value = "option", required = false) String option){
        // option = 최근 리뷰 등록 된 순, 가게 등록 최신, 가게 등록 오래 된 순, 리뷰 갯수 높은거, 평점

        if (logger.isDebugEnabled()){
            logger.debug("START. getStoreList");
        }
        try{
            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();

            storeSearchInfo.setStoreId(storeId);
            storeSearchInfo.setCategory(category);
            storeSearchInfo.setOption(option);

            storeService.getStoreList(storeSearchInfo);


            if (logger.isDebugEnabled()){
                logger.debug("END. getStoreList");
            }

            List<Map<String,Object>> storeList = new ArrayList<Map<String,Object>>();
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("storeList", storeSearchInfo.getStoreInfos());



            return new ResponseEntity<>(map, HttpStatus.OK);

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

            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();

            storeService.getMonthlyStore(storeSearchInfo);

            logger.debug("END. monthlyStore");

            List<Map<String,Object>> monthlyStoreList = new ArrayList<Map<String,Object>>();
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("storeList", storeSearchInfo.getStoreInfos());



            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            return "qwe";
        }

    }
}
