package org.zerock.ex00.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.ex00.common.paging.MyBoardPagingDTO;
import org.zerock.ex00.domain.MyBoardVO;
import org.zerock.ex00.mapper.MyBoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MyBoardServiceImpl implements MyBoardService {
	
	//매퍼 인터페이스 주입: 생성자를 이용한 자동 주입
	private MyBoardMapper myBoardMapper;
	// //MyBoardMapper를 주입받기 위한 생성자
	// public MyBoardServiceImpl(MyBoardMapper myBoardMapper) {
	// this.myBoardMapper = myBoardMapper;
	// }
	
	
	//게시물 목록 조회 서비스1
//	@Override
//	public List<MyBoardVO> getBoardList(){
//		log.info("MyBoardService.getBoardList() 실행");
//		return myBoardMapper.selectMyBoardList();
//	}
	
	//게시물 목록 조회 서비스 2
	@Override
	public List<MyBoardVO> getBoardList(MyBoardPagingDTO myBoardPagingDTO){
		log.info("MyBoardService.getBoardList() 실행");
		return myBoardMapper.selectMyBoardList(myBoardPagingDTO);
	}
	
	//게시물 총 개수 조회 서비스 - 페이징 시 필요
	@Override
	public Long getRowAmountTotal(MyBoardPagingDTO myBoardPagingDTO) {
		log.info("MyBoardService.getRowAmountTotal()에 전달된 MyBOardPagingDTO: "+myBoardPagingDTO);
		return myBoardMapper.selectRowAmountTotal(myBoardPagingDTO);
	};
		
	//게시물 등록:selectKey이용
	@Override
	public long registerBoard(MyBoardVO myBoard) {
		log.info("MyBoardService.registerBoard()에 전달된 MyBoardVO: " + myBoard);
		myBoardMapper.insertMyBoardSelectKey(myBoard);
		System.out.println("MyBoardService에서 등록된 게시물의 bno: " + myBoard.getBno());
		return myBoard.getBno();
	}
	
	//게시물 조회: by bno + 조회수 증가 고려
	@Override
	public MyBoardVO getBoard(Long bno) {
		log.info("MyBoardService.getBoard()에 전달된 bno: " + bno);
		//조회수 증가 후, bno 게시물 데이터 반환
		myBoardMapper.updateBviewsCnt(bno);
		return myBoardMapper.selectMyBoard(bno);
	}
	
	//게시물 수정 처리
	@Override
	public boolean modifyBoard(MyBoardVO myBoard){
		log.info("서비스에서의 게시물 수정 메소드(modify): " + myBoard);
		//게시물 정보 수정, 수정된 행수가 1 이면 True.
		return myBoardMapper.updateMyBoard(myBoard) == 1;
	}
	//게시물 삭제 - By 사용자: bdelFlag 컬럼만 1로 수정
	@Override
	public boolean setBoardDeleted(Long bno){
		log.info("MyBoardService.setBoardDeleted()에 전달된 bno: " + bno);
		return myBoardMapper.updateBdelFlag(bno) == 1;
	}
	 //게시물 삭제 - By 관리자: 실제 삭제 발생
	@Override
	public boolean removeBoard(Long bno){
		log.info("MyBoardService.removeBoard()에 전달된 bno: " + bno);
		return myBoardMapper.deleteMyBoard(bno) == 1;
	}
	//게시물 삭제(관리자) – 사용자 삭제 요청된 게시물 전체 삭제: bdelFlag = 1
	@Override
	public int removeAllDeletedBoard(){
		log.info("MyBoardService.removeAllDeletedBoard() 실행");
		//삭제된 총 행수 반환
		return myBoardMapper.deleteAllBoardSetDeleted() ;
	}
}