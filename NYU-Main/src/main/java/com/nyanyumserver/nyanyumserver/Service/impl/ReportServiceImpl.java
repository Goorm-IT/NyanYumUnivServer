package com.nyanyumserver.nyanyumserver.Service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nyanyumserver.nyanyumserver.Service.ReportService;
import com.nyanyumserver.nyanyumserver.VO.ReportSearchInfo;
import com.nyanyumserver.nyanyumserver.mapper.ReportMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final ReportMapper reportMapper;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void getReport(ReportSearchInfo reportSearchInfo){
		try{
			logger.debug("getReport Service START");
			reportMapper.getReport(reportSearchInfo);
			logger.debug("getReport Service END");
		}catch (Exception e){
			logger.error("getReport Service ERROR. : " + e);

		}
	}
}
