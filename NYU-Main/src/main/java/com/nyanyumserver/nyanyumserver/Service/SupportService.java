package com.nyanyumserver.nyanyumserver.Service;

import java.util.List;

import com.nyanyumserver.nyanyumserver.VO.SupportInfo;
import com.nyanyumserver.nyanyumserver.VO.SupportSearchInfo;

public interface SupportService {

	void getSupport(SupportInfo supportInfo);
	List<SupportSearchInfo> getSupportList(SupportSearchInfo supportSearchInfo);

}
