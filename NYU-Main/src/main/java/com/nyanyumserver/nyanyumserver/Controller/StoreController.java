package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Service.StoreService;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor

public class StoreController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StoreService storeService;


    @GetMapping("/StoreList")
    @ApiOperation(value = "가게 리스트")
    public Object getStoreList(){

        try{
            logger.debug("START.");
            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();

            storeService.getStoreList(storeSearchInfo);

            logger.debug("END");

            return new ResponseEntity<>(storeSearchInfo.getStoreInfos(), HttpStatus.OK);

        }catch (Exception e){
            logger.debug("ERROR, StoreList");

            return new ResponseEntity<>("불러올 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
