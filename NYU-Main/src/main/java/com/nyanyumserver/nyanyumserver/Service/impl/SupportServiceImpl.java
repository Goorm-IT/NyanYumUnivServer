package com.nyanyumserver.nyanyumserver.Service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nyanyumserver.nyanyumserver.Service.SupportService;
import com.nyanyumserver.nyanyumserver.VO.SupportInfo;
import com.nyanyumserver.nyanyumserver.mapper.SupportMapper;

import lombok.RequiredArgsConstructor;

@Service("SupportService")
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final SupportMapper supportMapper;

	@Override
	public void getSupport(SupportInfo supportInfo){

		try{
			supportMapper.getSupport(supportInfo);
		} catch (Exception e){
			logger.error("getSupport Error " + e);
		}

	}
}
