package com.nyanyumserver.nyanyumserver.Service;

import com.nyanyumserver.nyanyumserver.VO.ReportSearchInfo;

public interface ReportService {
	void getReport(ReportSearchInfo reportSearchInfo) throws Exception;
}
