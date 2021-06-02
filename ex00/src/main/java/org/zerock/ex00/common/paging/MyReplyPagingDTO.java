package org.zerock.ex00.common.paging;

import lombok.Data;

@Data
public class MyReplyPagingDTO {
	
	private long bno;
	private Integer pageNum;  //현재 페이지 번호  
	private int rowAmountPerPage;  //페이지당 출력할 레코드 개수 
	 
	public MyReplyPagingDTO(long bno, Integer pageNum) {
		this.bno = bno;
		
		if(pageNum == null) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
		
		this.rowAmountPerPage = 10;
	}

	  
	 	 
}
