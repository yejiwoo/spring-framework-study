package org.yejiwoo.sample.domain;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

// @Component // spring5버전부터는 vo클래스같은 경우는 컴포넌트 굳이 안 붙여도 자동으로 만들어 준다.
@Data
public class MyBoardVO {
	
	private long bno ; // 글 번호
	private String btitle ; //제목
	private String bcontent ; //내용
	private String bwriter ; //작성자
	private int bviewsCnt ;  //조회수
	private int breplyCnt ; //답글 수
	private int bdelFlag ;  //0: 유지(N, FALSE), 1: 삭제요청됨(Y, TRUE)
	private Date bregDate ; //등록일
	private Timestamp bmodDate ; //수정일


}
