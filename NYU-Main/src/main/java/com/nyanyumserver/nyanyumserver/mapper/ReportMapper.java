package com.nyanyumserver.nyanyumserver.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nyanyumserver.nyanyumserver.VO.ReportSearchInfo;

@Mapper
public interface ReportMapper {
	void getReport(ReportSearchInfo reportSearchInfo);
}
