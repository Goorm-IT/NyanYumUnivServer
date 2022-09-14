package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Common.CommonConst;
import com.nyanyumserver.nyanyumserver.Service.ImageService;
import com.nyanyumserver.nyanyumserver.Service.StoreService;
import com.nyanyumserver.nyanyumserver.VO.StoreSearchInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EnableAsync
@RestController
@EnableSwagger2
@RequiredArgsConstructor
@RequestMapping(value = "/nyu")
public class StoreController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StoreService storeService;
    private final ImageService imageService;

    @GetMapping(value = "/stores")
    @ApiOperation(value = "가게 리스트")
    public Object getStoreList(
            @ApiParam(value = "startPageNo", required = true) @RequestParam(value = "startPageNo", required = true) int startPageNo,
            @ApiParam(value = "endPageNo", required = true) @RequestParam(value = "endPageNo", required = true) int endPageNo,
            @ApiParam(value = "storeAlias", required = false) @RequestParam(value = "storeAlias", required = false) String storeAlias,
            @ApiParam(value = "category", required = false) @RequestParam(value = "category", required = false) String category,
            @ApiParam(value = "order" , required = false) @RequestParam(value = "order", required = false) String order)
    {
        // order = 최근 리뷰 등록 된 순, 가게 등록 최신, 가게 등록 오래 된 순, 리뷰 갯수 높은거, 평점

        if (logger.isDebugEnabled()){
            logger.debug("START. getStoreList");
        }
        try{
            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();

            storeSearchInfo.setStartPageNo(startPageNo - 1);
            storeSearchInfo.setEndPageNo(endPageNo);
            storeSearchInfo.setStoreAlias(storeAlias);
            storeSearchInfo.setCategory(category);
            storeSearchInfo.setOrder(order);

            storeService.getStoreList(storeSearchInfo);


            if (logger.isDebugEnabled()){
                logger.debug("END. getStoreList");
            }

            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("startPageNo" , storeSearchInfo.getEndPageNo());

            map.put("storeList", storeSearchInfo.getStoreInfos());
            map.put("pageInfo", map2);


            return new ResponseEntity<>(map, HttpStatus.OK);

        }catch (Exception e){
            logger.error("ERROR, getStoreList");
            return new ResponseEntity<>(CommonConst.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/store")
    @ApiOperation(value= "가게 추가")
    public Object addStore(
            @ApiParam(value = "storeAlias", required= true) @RequestParam(value = "storeAlias", required =true) String storeAlias,
            @ApiParam(value = "address", required= true) @RequestParam(value = "address", required=true) String address,
            @ApiParam(value = "category", required= true) @RequestParam(value = "category", required=true) String category,
            @ApiParam(value = "file", required= false) @RequestPart(value = "file", required=false) MultipartFile file,
            @ApiParam(value = "mapX", required= true) @RequestParam(value = "mapX", required=true) String mapX,
            @ApiParam(value = "mapY", required= true) @RequestParam(value = "mapY", required=true) String mapY,
            @ApiIgnore HttpSession session, HttpServletResponse response) {

        if (logger.isDebugEnabled()) {
            logger.debug("START. addStore");
        }
        StoreSearchInfo storeSearchInfo = new StoreSearchInfo();

        storeSearchInfo.setStoreAlias(storeAlias);
        storeSearchInfo.setAddress(address);
        storeSearchInfo.setCategory(category);
        storeSearchInfo.setMapX(mapX);
        storeSearchInfo.setMapY(mapY);

        if (storeService.getStoreId(storeSearchInfo) != null || storeService.getAddress(storeSearchInfo) != null) {
            return new ResponseEntity<>(CommonConst.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            try {
                if(file != null){
                String imgPath = imageService.updateImage(file, storeAlias, "store");
                storeSearchInfo.setImagePath(imgPath);
                }
                storeService.getRegister(storeSearchInfo);
                logger.debug("END. addStore");
                return new ResponseEntity<>("가게 추가를 완료 했습니다.", HttpStatus.OK);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    logger.error("ERROR. addStore");
                }
                return new ResponseEntity<>("가게 추가에 실패하였습니다.", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/store/{storeAlias}")
    @ApiOperation(value = "가게 정보")
    public Object getStoreInfo(
            @ApiParam(value = "가게 ID", required = true) @PathVariable(value = "storeAlias", required = true) String storeAlias
    ){
        try{
            if (logger.isDebugEnabled()) {
                logger.debug("START. storeInfo");
            }

            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();
            storeSearchInfo.setStoreAlias(storeAlias);

            storeService.getStoreInfo(storeSearchInfo);

            logger.debug("END. storeInfo");

            return new ResponseEntity<>(storeSearchInfo.getStoreInfos(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(CommonConst.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/store/monthly")
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
            return new ResponseEntity<>(CommonConst.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/store/search")
    @ApiOperation(value= "가게 검색")
    public Object getSearchStore(
            @ApiParam(value = "가게 명", required = true) @RequestParam(value = "storeAlias", required = true) String storeAlias,
            @ApiParam(value = "검색옵션", required = true) @RequestParam(value = "order", required = true) String order
    ){
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("START. searchStore");
            }
            StoreSearchInfo storeSearchInfo = new StoreSearchInfo();

            storeSearchInfo.setStoreAlias(storeAlias);
            storeSearchInfo.setOrder(order);

            storeService.getSearchStore(storeSearchInfo);

            logger.debug("END. searchStore");

            return new ResponseEntity<>(storeSearchInfo.getStoreInfos(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(CommonConst.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
