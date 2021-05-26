package org.zerock.ex00.service;
	
import java.util.List;
import org.zerock.ex00.domain.MyBoardVO;

public interface MyBoardService {
	//게시물 목록 조회 서비스1
	public List<MyBoardVO> getBoardList();
	
	//게시물 등록: selectKey이용
	public long registerBoard(MyBoardVO myBoard);
	
	//게시물 조회: by bno + 조회수 증가 고려
	public MyBoardVO getBoard(Long bno);
	
	//게시물 수정
	public boolean modifyBoard(MyBoardVO myBoard);
	
	//게시물 삭제 - By 사용자: bdelFlag 컬럼만 1로 수정
	public boolean setBoardDeleted(Long bno);
	
	 //게시물 삭제 - By 관리자: 실제 삭제 발생
	public boolean removeBoard(Long bno);
	
	
	//게시물 삭제(관리자) – 사용자 삭제 요청된 게시물 전체 삭제: bdelFlag = 1
	public int removeAllDeletedBoard();
}