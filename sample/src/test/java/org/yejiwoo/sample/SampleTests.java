package org.yejiwoo.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration    //테스트 시, DispatcherServlet의 servlet-context.xml 설정 구성파일(들)을 사용하기 위한 어노테이션
@RunWith(SpringJUnit4ClassRunner.class) //현재 테스트 코드가 스프링을 실행하는 역할을 할 것이라는 것을 표시

//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") 
@Log4j //lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성
public class SampleTests {

	@Setter(onMethod_ = {@Autowired})
	private Restaurant restaurant;
	
	@Test
	public void testExist() {
		assertNotNull(restaurant); //restaurant 변수가 null이 아니어야만 테스트 성공
		
		log.info(restaurant);
		log.info("---------------");
		log.info(restaurant.getChef());
		
	}
}
