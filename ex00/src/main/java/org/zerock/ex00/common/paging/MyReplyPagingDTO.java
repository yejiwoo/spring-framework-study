package org.zerock.ex00.common.paging;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MyReplyPagingDTO {

	private long bno; //게시물 번호
	private Integer pageNum; //현재 페이지 번호
	private int rowAmountPerPage; //페이지당 출력할 레코드 개수
	
	//생성자 - 댓글의 페이징번호 클릭 시,
	//pageNum과 rowAmountPerPage를 각각 사용자가 선택한 pageNum과 10으로 전달
	public MyReplyPagingDTO(long bno, Integer pageNum) {
		this.bno=bno;
		
		if(pageNum==null) this.pageNum=1;
		else this.pageNum=pageNum;
		
		this.rowAmountPerPage=10;
	}
	
}
