package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Common.CommonConst;
import com.nyanyumserver.nyanyumserver.Common.CommonResponse;
import com.nyanyumserver.nyanyumserver.Service.LikeService;
import com.nyanyumserver.nyanyumserver.VO.LikeSearchInfo;
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
@RequestMapping(value = "/nyu/like")
public class LikeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final LikeService likeService;

    @GetMapping("")
    @ApiResponse(code = 200, message = "{'show' = 1} : True(좋아요 누른 상태)\n{'show' = 0} : False(좋아요 안누른 상태)")
    @ApiOperation(value = "가게 좋아요 여부 확인", notes="로그인 상태에서만 가능합니다.\n가게 상세 페이지 상단 - 아이콘 색상 On(True) / Off(False)")
    public Object getLike(
            @ApiIgnore HttpSession session,
            @ApiParam(value = "가게 ID", required = true)
            @RequestParam(value = "storeId", required = true) int storeId
    ){
        try{
            if (logger.isDebugEnabled()) {
                logger.debug("START. getLike");
            }
            LikeSearchInfo likeSearchInfo = new LikeSearchInfo();

            String userAlias = (String) session.getAttribute("userAlias");
            if (userAlias.isEmpty()){
                throw new NullPointerException();
            }
            likeSearchInfo.setUserAlias(userAlias);
            likeSearchInfo.setStoreId(storeId);
            int flag = likeService.getLike(likeSearchInfo);
            if (flag == -1) flag = 0;

            Map<String, Object> result = new HashMap<>();
            result.put("userAlias", userAlias);
            result.put("storeId", storeId);
            result.put("show", flag);

            logger.debug("END. getLike");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    @ApiOperation(value = "금월 사용자가 '좋아요' 누른 가게 리스트", notes = "로그인 상태에서만 가능합니다.\n가장 최근에 좋아요 누른 순으로 정렬됩니다.")
    public Object getUserLikeList(
            @ApiIgnore HttpSession session
    ){
        if (logger.isDebugEnabled()) {
            logger.debug("START. getUserLikeList");
        }
        try{
            LikeSearchInfo likeSearchInfo = new LikeSearchInfo();
            String userAlias = (String) session.getAttribute("userAlias");

            if (userAlias.isEmpty()){
                throw new NullPointerException();
            }
            likeSearchInfo.setUserAlias(userAlias);
            likeService.getUserLikeList(likeSearchInfo);

            Map<String, Object> map = new HashMap<>();
            map.put("userLikeList", likeSearchInfo.getLikeInfos());

            logger.debug("END. getUserLikeList");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    @ApiOperation(value= "가게 좋아요 On/Off", notes = "로그인 상태에서만 가능합니다.\n좋아요 수는 매월 초기화 됩니다.")
    public Object setLike(
            @ApiIgnore HttpSession session,
            @ApiParam(value = "storId", required= true)
            @RequestParam(value = "storeId") Integer storeId
    ) {

        if (logger.isDebugEnabled()) {
            logger.debug("START. setLike");
        }
        try {
            LikeSearchInfo likeSearchInfo = new LikeSearchInfo();

            String userAlias = (String) session.getAttribute("userAlias");

            if (userAlias.isEmpty()){
                throw new NullPointerException();
            }
            likeSearchInfo.setUserAlias(userAlias);
            likeSearchInfo.setStoreId(storeId);

            int flag = likeService.getLike(likeSearchInfo);
            likeSearchInfo.setShow(flag);
            if (flag == -1) {
                likeService.createLike(likeSearchInfo);
                likeSearchInfo.setShow(0);
            }
            likeService.setLike(likeSearchInfo);
            likeService.setLikeCount(likeSearchInfo);
            logger.debug("END. setLike");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.OK), HttpStatus.OK);

        }catch (NullPointerException e){
            logger.debug("END. setLike");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("END. setLike");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

    }
}