package com.nyanyumserver.nyanyumserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nyanyumserver.nyanyumserver.VO.SupportInfo;
import com.nyanyumserver.nyanyumserver.VO.SupportSearchInfo;

@Mapper
public interface SupportMapper {
	void getSupport(SupportInfo supportInfo);
	List<SupportInfo> getSupportList(SupportSearchInfo supportSearchInfo);
}
