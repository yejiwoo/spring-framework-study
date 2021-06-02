package org.zerock.ex00.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.ex00.common.paging.MyBoardPagingDTO;
import org.zerock.ex00.domain.MyBoardVO;

public interface MyBoardMapper {
	//게시물 조회 - 목록
	//public List<MyBoardVO> selectMyBoardList();
	
	//게시물 조회 - 목록(페이징 고려)
	public List<MyBoardVO> selectMyBoardList(MyBoardPagingDTO myBoardPagingDTO); 
	
	//게시물 조회 - 총 게시물 개수(페이징 데이터)
	//public Long selectRowAmountTotal(MyBoardPagingDTO myBoardPagingDTO); 검색조건을 구현했을 경우에 사용
	public Long selectRowAmountTotal(MyBoardPagingDTO myBoardPagingDTO);

	//게시물 등록1 - selectKey 이용안함
	public void insertMyBoard(MyBoardVO myBoard);

	//게시물 등록2 - selectKey 이용
	public void insertMyBoardSelectKey(MyBoardVO myBoard);

	//게시물 조회
	public MyBoardVO selectMyBoard(long bno);

	//게시물 삭제 - bdelFlag를 1로 수정
	public int updateBdelFlag(long bno);

	//게시물 삭제 - 실제 삭제 발생
	public int deleteMyBoard(long bno);

	//게시물 삭제(관리자) - dbelFalg = 1
	public int deleteAllBoardSetDeleted();

	//게시물 수정
	public int updateMyBoard(MyBoardVO myBoard);

	//게시물 조회수 증가
	public void updateBviewsCnt(long bno);
	
	//댓글 개수 증가 & 감소
	public void updateBReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
	
}
