package org.zerock.ex00.common.paging;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MyBoardPagingDTO {

	private int pageNum;	//현재 페이지 번호
	private int rowAmountPerPage;	//페이지당 출려갈 레코드 개수
	
	private String scope; //검색범위(scope = T:title, C:content, W:writer)
	private String keyword; //검색어
	
	//검색 범위 값 처리를 위한 메소드
	//화면에서 선택된 tcw 값이 scope 필드를 통해 매퍼-XML 파일에서 scopeArray 변수로 처리될 때
	//TCW -> ["T", "C", "W"]배열로 변환
	//Mybatis 엔진이 매퍼-XML파일의 scopeArray 컬렉션에서 값을 추출하기 위한 용도로 사용함(자동)
	//따라서, 메서드 이름이 scopeArray에 대한 getter 형식을 지켜야 합니다.
	public String[] getScopeArray() {
		return scope==null? new String[] {} : scope.split("");
	}
	
	//생성자를 통해 표시할 페이지번호와 페이지당 출력할 레코드 개수를 컨트롤러로 전달
	//생성자1: list.jsp가 "처음 호출" 시에 페이지번호와 행수를 각각 1과 10으로 전달
	public MyBoardPagingDTO() {
		this.pageNum=1;
		this.rowAmountPerPage=10;
	}
	
	//생성자2: 목록 화면에서 페이징번호 클릭 시, 사용자가 선택한 페이징번호 전달
	public MyBoardPagingDTO(int pageNum) {
		if(pageNum<=0) this.pageNum=1;
		else this.pageNum=pageNum;
		
		this.rowAmountPerPage=10;
	}
	
	//생성자3: 목록 화면에서 사용자가 표시할 행수를 선택하고 페이징번호 클릭 시, 사용자가 선택한 페이징번호와 표시행수 전달
	public MyBoardPagingDTO(int pageNum, int rowAmountPerPage) {
		if(pageNum<=0) this.pageNum=1;
		else this.pageNum=pageNum;
		
		if(rowAmountPerPage<=0) this.rowAmountPerPage=10;
		else this.rowAmountPerPage=rowAmountPerPage;
	}
	
	
}
