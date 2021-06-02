package org.zerock.ex00.service;

import java.util.List;

import org.zerock.ex00.common.paging.MyBoardPagingDTO;
import org.zerock.ex00.domain.MyBoardVO;

public interface MyBoardService {
	
	//게시물 목록 조회 서비스1
	//public List<MyBoardVO> getBoardList();
	
	//게시물 목록 조회 서비스2 (페이징 고려)
	public List<MyBoardVO> getBoardList(MyBoardPagingDTO myBoardPagingDTO);
	
	//게시물 총 개수 조회 서비스 - 페이징 시 필요 
	public Long getRowAmountTotal(MyBoardPagingDTO myBoardPagingDTO);
	
	//게시물 등록: selectKey이용
	public long registerBoard(MyBoardVO myBoard);
	
	public MyBoardVO getBoard(Long bno);
	
	public boolean modifyBoard(MyBoardVO myBoard);
	
	public boolean setBoardDeleted(Long bno);
	
	public boolean removeBoard(Long bno);
	
	public int removeAllDeletedBoard();
	
}
