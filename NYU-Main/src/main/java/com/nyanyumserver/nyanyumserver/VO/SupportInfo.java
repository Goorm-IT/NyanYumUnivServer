package com.nyanyumserver.nyanyumserver.VO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SupportInfo {
	int supportId;
	String userAlias;
	String type;
	String category;
	int reviewId;
	String content;
	LocalDate registerDate;
	int reply;
}
