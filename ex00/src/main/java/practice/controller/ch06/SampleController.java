package practice.controller.ch06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j //이거 없으면 Log 못 씀당융
public class SampleController {
	
	@RequestMapping("") //get방식이 default
	public void basic() {
		log.info("basic...................");
	}
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get...................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet1() {
		//@PostMapping(행삽입), @DeleteMapping(행삭제), @PutMapping(행수정) 암기~~~ (select문은 Get)
		log.info("basicGet1 basicOnlyGet URL...................");
	}
	
	//컨트롤러 메소드의 반환타입을 String으로 설정한 경우 해당 스트링은 사용자 브라우저로 전달된 동적 html을 생성하는 jsp파일이름(확장자가 제거된) 이름이어야 합니다. servlet-context.xml 설명 참고
	@GetMapping("/ex01")
	//url에 dto의 필드 값을 준다.
	public String ex01(SampleDTO dto) {
		log.info(""+dto);
		System.out.println(dto.getName());
		System.out.println(dto.getAge());
		return "ex01"; //ViewResolver에게 준다. //리턴값이 없으면 /WEB-INF/views/(<=접두어)sample/ex01(접미어=>).jsp를 찾는다.
	}
	
	@GetMapping("/ex02")
	//VO객체 없이도 사용 가능. 웹브라우저가 준 값을 매개변수로 받는다.
	//서블릿의 request.getParameter
	public String ex02(@RequestParam("name") String name1, @RequestParam("age") int age1) {
		
		log.info("name: "+name1);
		log.info("age:" +age1);
		
		return "ex02";
	}
}
