package org.yejiwoo.sample.MyBoard;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.yejiwoo.sample.domain.MyBoardVO;
import org.yejiwoo.sample.service.MyBoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration //테스트시 DispatcherServlet의 servlet-context.xml 설정 구성파일을 사용하기 위한 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/mybatis-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class MyBoardServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private MyBoardService myBoardService;
	
//	//myBoardService 빈 생성 유무 확인 테스트
//	@Test
//	public void testMyBoardServiceExist() {
//		log.info(myBoardService);
//		assertNotNull(myBoardService);
//	}
//	
//	//게시물 목록 조회 서비스1 테스트
//	@Test
//	public void testGetBoardList() {
//		//페이징 고려 안 함
//		myBoardService.getBoardList().forEach(myBoard -> log.info(myBoard));		
//	}
//	
//	//게시물 등록(selectKey 이용) 테스트
//	@Test
//	public void testRegisterBoard() {
//		MyBoardVO myboard=new MyBoardVO();
//		myboard.setBtitle("서비스 새글 입력 테스트 제목");
//		myboard.setBcontent("서비스 새글 입력 테스트 내용");
//		myboard.setBwriter("test");
//		
//		myBoardService.registerBoard(myboard);
//		log.info("등록된 게시물의 bno: " + myboard.getBno());
//	}
//	
//	//게시물 조회 테스트: by bno + 조회수 증가 고려
//	@Test
//	public void testGetBoard() {
//		log.info(myBoardService.getBoard(1L));
//	}
//		
//	//게시물 수정 테스트
//	@Test
//	public void testModifyBoard() {
//		MyBoardVO myboard=myBoardService.getBoard(1L);
//		
//		if(myboard==null) return ;
//		
//		myboard.setBtitle("제목 수정: 서비스 테스트");
//		log.info("수정된 게시물 조회 결과(boolean): "+myBoardService.modifyBoard(myboard));
//		
//	}
	
//	//게시물 삭제 테스트 by 사용자 - bdelFlag 컬럼만 1로 수정
//	@Test
//	public void testSetBoardDeleted() {
//		//게시물 번호의 존재 여부를 확인하고 테스트할 것
//		log.info("수행결과: "+myBoardService.setBoardDeleted(7L));
//		log.info("수행결과: "+myBoardService.setBoardDeleted(8L));
//		log.info(myBoardService.getBoard(7L));
//		log.info(myBoardService.getBoard(8L));
//	}
//	
//	//게시물 삭제 테스트 - 실제 삭제 발생
//	@Test
//	public void testRemoveBoard() {
//		//게시물 번호의 존재 여부를 확인하고 테스트할 것
//		log.info("서비스: 특정 게시물 삭제 테스트: "+myBoardService.removeBoard(6L));
//	}
	
	//게시물 삭제 테스트(관리자) - 삭제 요청된 게시물 전체 삭제
	@Test
	public void testRemoveAllDeletedBoard() {
		log.info("서비스: 삭제 설정된 모든 게시물 삭제: 삭제된 행 수: "+myBoardService.removeAllDeletedBoard());
	}
}
