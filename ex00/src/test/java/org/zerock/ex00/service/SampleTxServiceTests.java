package org.zerock.ex00.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/mybatis-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class SampleTxServiceTests {

	@Setter(onMethod_= {@Autowired})
	private SampleTxService service;
	
	@Test
	public void testLong() {
		String str="Starry\r\nStarry night\r\nPaint your palette blue and grey\r\nLook out on a summer's day";
		
		log.info(str.getBytes().length);
		service.addData(str);
	}
}
