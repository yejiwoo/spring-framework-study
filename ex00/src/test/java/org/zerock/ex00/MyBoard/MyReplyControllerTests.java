package org.zerock.ex00.MyBoard;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.ex00.domain.MyReplyVO;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//테스트 메소드를 실행하는 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //DispatcherServlet 의 구성설정파일을 사용하여 컨트롤러를 테스트하기 위해 필요한 설정
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/mybatis-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class MyReplyControllerTests {

	//private static final Logger logger = LoggerFactory.getLogger(MyReplyControllerTests.class);

	//테스트 환경 구성
	//컨트롤러 테스트를 위해서는 WebApplicationContext 를 객체(웹 컨텍스트 객체)로 주입 받아야 합니다.
	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;
	
	//테스트 객체를 담을 필드
	private MockMvc mockMvc;
	
	//테스트 전에 테스트 객체를 생성하여 테스트 환경 설정
	@Before
	public void setup() {
		 this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	//테스트 환경 구성-끝

	//게시물에 대한 댓글 목록 조회 테스트: GET /replies/pages/{bno}/{page}
	@Test
	public void testShowReplyList() throws Exception {
		int resultStatus =
		 mockMvc.perform(get("/replies/pages/458752/1")
		 //서버가 보낸 것이 JSON 일 때만 처리(컨트롤러의 produces 에 대응)
		 .accept("application/json; charset=UTF-8")
		 //서버로 보내는 데이터에 대한 인코딩을 UTF-8 로 지정
		 .characterEncoding("utf-8")
		 )
		 .andDo(print())// 콘솔 출력
		 .andReturn()
		 .getResponse()
		 .getStatus();
		log.info("웹브라우저에 전달되는 ResponseEntiry 객체의 처리 상태 코드(resultStatus): " + resultStatus );
	}
	
	//게시물에 대한 댓글 등록(rno 반환):POST /replies/{bno}/new (브라우저에서 JSON 으로 보내는 것처럼 테스트 코드 작성)
	@Test
	public void testRegisterReplyForBoard() throws Exception {
		//MyReplyVO 객체 생성
		 MyReplyVO myReply = new MyReplyVO();
	
		//myReplyVO 에 입력값 지정
		myReply.setBno(458751L);
		myReply.setRcontent("컨트롤러-게시물에 대한 댓글 등록 테스트: JSON 입력테스트");
		myReply.setRwriter("testJson");
		//myReply 를 JSON 스트링으로 변환
		 String myReplyJsonStr = new Gson().toJson(myReply);
		//JsonString 객체 콘솔출력
		log.info("컨트롤러-게시물에 대한 댓글 등록 테스트: JSON-String 객체(myReplyJsonStr):"+myReplyJsonStr);
		//ResponseEntiry 가 반환하는 상태값을 표시
		 int resultStatus
		 = mockMvc.perform(post("/replies/458751/new")
		 //서버가 보낸 것이 String 일 때만 처리(컨트롤러의 @*Mapping 의 produces 속성에 대응)
		 .accept("text/plain; charset=UTF-8")
		 //서버에게 보낼 것이 JSON 임을 header 에 명시(컨트롤러의 @*Mapping 의 consumes 속성에 대응)
		 .contentType("application/json; charset=UTF-8")
		 //보내는 데이터에 대한 인코딩 설정입니다.
		 .characterEncoding("utf-8")
		 //테스트에 처리되는 데이터
		 .content(myReplyJsonStr)
		 )
		 .andDo(print())//print()를 직접 static 으로 import, 콘솔에 출력
		 .andReturn()
		 .getResponse()
		 .getStatus();
		 log.info("웹브라우저에 전달되는 ResponseEntiry 객체의 처리 상태 코드(resultStatus): " + resultStatus);
	} 

	//게시물에 대한 댓글의 댓글 등록: (rno 반환): POST /replies/{bno}/{prno}/new
	@Test
	public void testRegisterReplyForReply() throws Exception {
		//replyVO 객체 생성
		 MyReplyVO myReply = new MyReplyVO();
	
		//replyVO 에 입력값 지정
		myReply.setBno(458751L);
		myReply.setRcontent("컨트롤러-댓글에 대한 댓글 등록 테스트");
		myReply.setRwriter("test00");
		myReply.setPrno(51L); //부모댓글의 rno 를 받아와야 함
		//myReply 를 JSON 스트링으로 변환
		 String myReplyJsonStr = new Gson().toJson(myReply);
		//JsonString 객체 콘솔출력
		log.info("컨트롤러-게시물에 대한 댓글 등록 테스트: JSON-String 객체(myReplyJsonStr):"+myReplyJsonStr);
		//ResponseEntiry 가 반환하는 상태값을 표시
		 int resultStatus = mockMvc.perform( post("/replies/458751/51/new")
		 //서버가 보낸 것이 String 일 때만 처리(컨트롤러의 @*Mapping 의 produces 속성에 대응)
		 .accept("text/plain; charset=UTF-8")
		 //서버에게 보낼 것이 JSON 임을 header 에 명시(컨트롤러의 @*Mapping 의 consumes 속성에 대응)
		 .contentType("application/json; charset=UTF-8")
		 //보내는 데이터에 대한 인코딩 설정입니다.
		 .characterEncoding("utf-8")
		 //테스트에 처리되는 데이터
		 .content(myReplyJsonStr)
		 )
		 .andDo(print())//print()를 직접 static 으로 import, 콘솔에 출력
		 .andReturn()
		 .getResponse()
		 .getStatus();
		 log.info("웹브라우저에 전달되는 ResponseEntiry 객체의 처리 상태 코드(resultStatus): " + resultStatus); 
	}
	
	//게시물에 대한 특정 댓글 조회 테스트: GET /replies/{bno}/{rno}
	@Test
	public void testShowReply() throws Exception{

		int resultStatus =
		 mockMvc.perform(get("/replies/458751/53")
		 //서버가 보낸 것이 JSON 일 때만 처리(컨트롤러의 produces 에 대응)
		 .accept("application/json; charset=UTF-8")
		 //서버로 보내는 데이터에 대한 인코딩을 UTF-8 로 지정
		 .characterEncoding("utf-8")
		 ) 
		 .andDo(print())// 콘솔 출력
		 .andReturn()
		 .getResponse()
		 .getStatus();
	
		log.info("웹브라우저에 전달되는 ResponseEntiry 객체의 처리 상태 코드(resultStatus): " + resultStatus );
	}

	//게시물에 대한 특정 댓글 수정 테스트 : put /replies/{bno}/{rno}, patch /replies/{bno}/{rno}
	@Test
	public void testModifyReply() throws Exception{
	 MyReplyVO myReply = new MyReplyVO() ;
	myReply.setRno(53L);

	myReply.setRcontent("댓글-컨트롤러-수정테스트 - 댓글 수정(put)");

	//myReply 를 JSON 스트링으로 변환
	 String myReplyJsonStr = new Gson().toJson(myReply);

	 System.out.println("===========JSON-String 객체로 변환:" + myReplyJsonStr);
	int resultStatus =
	 mockMvc.perform(put("/replies/458751/53")
	 //mockMvc.perform(patch("/replies/458751/53")
	 //서버가 보낸 것(컨트롤러의 @*Mapping 의 produces 속성에 대응)
	 .accept("text/plain; charset=UTF-8")
	 //서버에게 보낼 것(컨트롤러의 @*Mapping 의 consumes 속성에 대응)
	 .contentType("application/json; charset=UTF-8")
	 .content(myReplyJsonStr)
	 )
	 .andDo(print())// 콘솔 출력
	 .andReturn() 
	 .getResponse()
	 .getStatus();

	log.info("웹브라우저에 전달되는 ResponseEntiry 객체의 처리 상태 코드(resultStatus): " + resultStatus);
	}
	
	//게시물에 대한 특정 댓글 삭제 테스트: DELETE: /replies/{bno}/{rno}
	@Test
	public void testRemoveReply1() throws Exception{

	int resultStatus =
	 mockMvc.perform(delete("/replies/458751/53").accept("text/plain; charset=UTF-8"))
	 .andDo(print())
	 .andReturn()
	 .getResponse()
	 .getStatus();

	log.info("웹브라우저에 전달되는 ResponseEntiry 객체의 처리 상태 코드(resultStatus): " + resultStatus );
	}
	
}
