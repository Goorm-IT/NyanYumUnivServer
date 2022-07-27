package com.nyanyumserver.nyanyumserver.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nyanyumserver.nyanyumserver.VO.SupportInfo;

@Mapper
public interface SupportMapper {
	void getSupport(SupportInfo supportInfo);
}
