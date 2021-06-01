package org.zerock.ex00.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MyReplyVO {

	private long bno; //게시글 번호
	private long rno; //댓글 번호
	private String rcontent; //댓글내용
	private String rwriter; //댓글 작성자
	private Timestamp rregDate; //댓글 작성일
	private Timestamp rmodDate; //댓글 수정일
	private long prno; //부모 댓글 번호
	private int lvl; //계층쿼리의 level 값을 저장할 필드
}
