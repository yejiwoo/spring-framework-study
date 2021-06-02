package org.yejiwoo.sample.mapper;

import java.util.List;

import org.yejiwoo.sample.domain.MyBoardVO;

public interface MyBoardMapper {

	//게시물 조회 - 목록
	public List<MyBoardVO> selectMyBoardList();
	
	//게시물 등록1 - selectKey 이용 안 함
	public Integer insertMyBoard(MyBoardVO myBoard);
	
	//게시물 등록2 - selectKey 이용
	public Integer insertMyBOardSelectKey(MyBoardVO myBoard);
	
	//게시물 조회
	public MyBoardVO
}
