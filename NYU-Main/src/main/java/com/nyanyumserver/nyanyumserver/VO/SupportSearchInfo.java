package com.nyanyumserver.nyanyumserver.VO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SupportSearchInfo extends PageInfo implements Serializable {
	private int supportId;
	private String userAlias;
	private String type;
	private String category;
	private int reviewId;
	private String content;
	private LocalDate registerDate;
	private int reply;
	private List<SupportInfo> supportInfos = new ArrayList<>();
	public void addSupportInfos(List<SupportInfo> supportInfos) {
		this.supportInfos.addAll(supportInfos);
	}
}
