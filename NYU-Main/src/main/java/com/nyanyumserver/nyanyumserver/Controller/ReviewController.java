package com.nyanyumserver.nyanyumserver.Controller;

import com.nyanyumserver.nyanyumserver.Common.CommonConst;
import com.nyanyumserver.nyanyumserver.Common.CommonResponse;
import com.nyanyumserver.nyanyumserver.Service.ImageService;
import com.nyanyumserver.nyanyumserver.Service.ReviewService;
import com.nyanyumserver.nyanyumserver.VO.ReviewSearchInfo;
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
import java.util.HashMap;
import java.util.Map;


@EnableAsync
@RestController
@EnableSwagger2
@RequiredArgsConstructor
@RequestMapping(value = "/nyu/review")
public class ReviewController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewService reviewService;
    private final ImageService imageService;


    @GetMapping("")
    @ApiOperation(value = "단일 리뷰 로드")
    public Object getReview(
            @ApiParam(value = "리뷰 ID", required = true)
            @RequestParam(value = "reviewId", required = true) Integer reviewId
    ){
        try{
            if (logger.isDebugEnabled()) {
                logger.debug("START. getReview");
            }

            ReviewSearchInfo reviewSearchInfo = new ReviewSearchInfo();
            reviewSearchInfo.setReviewId(reviewId);

            reviewService.getReview(reviewSearchInfo);

            if (reviewSearchInfo.getReviewInfos().isEmpty()){
                throw new NullPointerException();
            }

            logger.debug("END. getReview");

            return new ResponseEntity<>(reviewSearchInfo.getReviewInfos(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/store")
    @ApiOperation(value = "가게별 리뷰 로드")
    public Object getReviewList(
            @ApiParam(value = "가게 ID", required = true)
            @RequestParam(value = "storeId", required = true) Integer storeId
    ){
        if (logger.isDebugEnabled()) {
            logger.debug("START. getReviewList");
        }
        try{
            ReviewSearchInfo reviewSearchInfo = new ReviewSearchInfo();
            reviewSearchInfo.setStoreId(storeId);
            reviewService.getReviewList(reviewSearchInfo);

            // 리뷰 존재 X
            if (reviewSearchInfo.getReviewInfos().isEmpty())
                throw new NullPointerException();

            Map<String, Object> map = new HashMap<>();
            map.put("reviewList", reviewSearchInfo.getReviewInfos());

            logger.debug("END. getReviewList");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    @ApiOperation(value = "사용자 리뷰 로드", notes = "로그인 상태에서만 로드 가능합니다.")
    public Object getUserReviewList(
            @ApiIgnore HttpSession session
    ){
        if (logger.isDebugEnabled()) {
            logger.debug("START. getUserReviewList");
        }
        try{
            ReviewSearchInfo reviewSearchInfo = new ReviewSearchInfo();
            String userAlias = (String) session.getAttribute("userAlias");

            if (userAlias.isEmpty()){
                throw new NullPointerException();
            }
            reviewSearchInfo.setUserAlias(userAlias);
            reviewService.getUserReviewList(reviewSearchInfo);

            Map<String, Object> map = new HashMap<>();
            map.put("userReviewList", reviewSearchInfo.getReviewInfos());

            logger.debug("END. getUserReviewList");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/content")
    @ApiOperation(value = "가게별 리뷰 한줄평 로드", notes = "- 글자수 제한 X \n- 최대 5개 추출")
    public Object getReviewContent(
            @ApiParam(value = "가게 ID", required = true)
            @RequestParam(value = "storeId", required = true) Integer storeId
    ){
        if (logger.isDebugEnabled()) {
            logger.debug("START. getReviewContent");
        }
        try{
            ReviewSearchInfo reviewSearchInfo = new ReviewSearchInfo();
            reviewSearchInfo.setStoreId(storeId);
            reviewService.getReviewContent(reviewSearchInfo);

            // 리뷰 존재 X
            if (reviewSearchInfo.getReviewInfos().isEmpty())
                throw new NullPointerException();

            Map<String, Object> map = new HashMap<>();
            map.put("reviewList", reviewSearchInfo.getReviewInfos());

            logger.debug("END. getReviewContent");
            return new ResponseEntity<>(map, HttpStatus.OK);

        }catch (NullPointerException e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    @ApiOperation(value= "새 리뷰 추가", notes = "로그인 상태에서만 가능합니다.\n### <parameter file 설명 별첨>\n- file(*required) : 1개 제한/파일 최대 크기 : 2.5MB")
    public Object setReview(
            @ApiParam(value = "storeId", required= true) @RequestParam(value = "storeId") Integer storeId,
            @ApiParam(value = "menuId", required= true) @RequestParam(value = "menuId") Integer menuId,
            @ApiParam(value = "score", required= true) @RequestParam(value = "score") Float score,
            @ApiParam(value = "content", required= true) @RequestParam(value = "content") String content,
            @ApiParam(value = "file", required= true) @RequestPart(value = "file") MultipartFile file,
            @ApiIgnore HttpSession session, HttpServletResponse response
    ) {

        if (logger.isDebugEnabled()) {
            logger.debug("START. setReview");
        }
        ReviewSearchInfo reviewSearchInfo = new ReviewSearchInfo();

        String userAlias = (String) session.getAttribute("userAlias");

        if (userAlias.isEmpty()){
            throw new NullPointerException();
        }
        reviewSearchInfo.setUserAlias(userAlias);
        reviewSearchInfo.setStoreId(storeId);
        reviewSearchInfo.setMenuId(menuId);
        reviewSearchInfo.setScore(score);
        reviewSearchInfo.setContent(content);

        try {
            if(file != null){
                String reviewId = Integer.toString(reviewService.getReviewId(reviewSearchInfo));
                String imgPath = imageService.updateImage(file, reviewId, "review");
                reviewSearchInfo.setImagePath(imgPath);
            }
            reviewService.setReview(reviewSearchInfo);
            reviewService.setMenuCount(reviewSearchInfo);
            logger.debug("END. setReview");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.OK), HttpStatus.OK);

        }catch (NullPointerException e){
            logger.debug("END. setReview");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.NOT_FOUND), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("END. setReview");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("")
    @ApiOperation(value= "리뷰 삭제")
    public Object deleteReview(
            @ApiParam(value = "리뷰 ID", required = true)
            @RequestParam(value = "reviewId", required = true) Integer reviewId
    ) {

        if (logger.isDebugEnabled()) {
            logger.debug("START. deleteReview");
        }
        ReviewSearchInfo reviewSearchInfo = new ReviewSearchInfo();

        reviewSearchInfo.setReviewId(reviewId);

        try {
            reviewService.deleteReview(reviewSearchInfo);
            logger.debug("END. deleteReview");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.OK), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            logger.debug("END. deleteReview");
            return new ResponseEntity<>(CommonResponse.of(CommonConst.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }
}
