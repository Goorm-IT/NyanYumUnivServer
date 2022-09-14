package com.nyanyumserver.nyanyumserver.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nyanyumserver.nyanyumserver.Common.CommonConst;
import com.nyanyumserver.nyanyumserver.Service.SupportService;
import com.nyanyumserver.nyanyumserver.VO.ReportSearchInfo;
import com.nyanyumserver.nyanyumserver.VO.SupportInfo;
import com.nyanyumserver.nyanyumserver.VO.SupportSearchInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequiredArgsConstructor
@EnableSwagger2
@RequestMapping(value = "/nyu/support")
public class SupportController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final SupportService supportService;


	@PostMapping("")
	@ApiOperation(value ="건의하기")
	public Object support(
		@ApiParam(value = "유저 닉네임") @RequestParam(value = "userAlias", required = true) String userAlias,
		@ApiParam(value = "건의 타입") @RequestParam(value = "type", required = true) String type,
		@ApiParam(value = "건의 내용") @RequestParam(value = "content", required = true) String content,
		@ApiParam(value = "건의 카테고리") @RequestParam(value = "category", required = true) String category,
		@ApiParam(value = "리뷰 아이디") @RequestParam(value = "reviewId", required = true) int reviewId) {
		SupportInfo supportInfo = new SupportInfo();
		supportInfo.setUserAlias(userAlias);
		supportInfo.setType(type);
		supportInfo.setCategory(category);
		supportInfo.setContent(content);
		supportInfo.setReviewId(reviewId);

		try {
			supportService.getSupport(supportInfo);
			return new ResponseEntity<>(CommonConst.OK, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Support Controller Error" + e);
			return new ResponseEntity<>(CommonConst.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("")
	@ApiOperation(value="건의 조회하기")
	public Object getSupport(
		@ApiParam(value = "건의 카테고리") @RequestParam(value = "category", required = false) String category
	){
		SupportSearchInfo supportSearchInfo = new SupportSearchInfo();

		supportSearchInfo.setCategory(category);

		try{
			return new ResponseEntity<>(supportService.getSupportList(supportSearchInfo), HttpStatus.OK);
		}catch (Exception e){
			logger.error("getSupport Controller Error" + e);
			return new ResponseEntity<>(CommonConst.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}

}
