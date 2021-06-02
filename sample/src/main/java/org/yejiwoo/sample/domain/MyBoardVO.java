package org.yejiwoo.sample.domain;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

// @Component // spring5버전부터는 vo클래스같은 경우는 컴포넌트 굳이 안 붙여도 자동으로 만들어 준다.
@Data
public class MyBoardVO {
	
	private long bno ;
	private String btitle ;
	private String bcontent ;
	private String bwriter ;
	private int bviewsCnt ; 
	private int breplyCnt ;
	private int bdelFlag ;  //0: 유지(N, FALSE), 1: 삭제요청됨(Y, TRUE)
	private Date bregDate ;
	private Timestamp bmodDate ;


}


