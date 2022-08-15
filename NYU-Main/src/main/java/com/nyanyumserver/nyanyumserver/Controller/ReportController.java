package com.nyanyumserver.nyanyumserver.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nyanyumserver.nyanyumserver.Common.CommonConst;
import com.nyanyumserver.nyanyumserver.Service.ReportService;
import com.nyanyumserver.nyanyumserver.VO.ReportSearchInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequiredArgsConstructor
@EnableSwagger2
@RequestMapping(value = "/nyu/report")
public class ReportController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ReportService reportService;

	@PostMapping("")
	@ApiOperation(value="신고하기")
	public Object getReport(
		@ApiParam(value = "신고 리뷰 아이디")@RequestParam(value = "reviewId", required = true) int reviewId,
		@ApiParam(value = "신고내용") @RequestParam(value="report", required = true) String report
		) throws Exception {
		ReportSearchInfo reportSearchInfo = new ReportSearchInfo();

		reportSearchInfo.setReviewId(reviewId);
		reportSearchInfo.setReport(report);


		try{
			reportService.getReport(reportSearchInfo);

			return new ResponseEntity<>(CommonConst.OK, HttpStatus.OK);
		}catch (Exception e){
			logger.error("getReport Controller Error" + e);
			return new ResponseEntity<>(CommonConst.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}
	// @GetMapping("/report")
	// @ApiOperation(value = "신고하기 전체 목록")
	// public Object getReportList(){
	//
	// }


}
