package org.yejiwoo.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yejiwoo.sample.domain.MyBoardVO;
import org.yejiwoo.sample.service.MyBoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/myboard")
public class MyBoardController {

	@Setter(onMethod_= {@Autowired})
	private MyBoardService myboardService;
	
	//게시물 목록 조회1
	@GetMapping("/list")
	public void showBoardList(Model model) {
		log.info("컨트롤러 - 게시물 목록 조회....");
		model.addAttribute("boardList",myboardService.getBoardList());
		//return 값이 없으므로 호출경로(/myboard/list)와 같은 폴더의 파일이 실행된다.
	}
	
	//게심ㄹ 등록 -페이지 호출
	@GetMapping("/register")
	public void showBoardRegisterPage() {
		log.info("컨트롤러 - 게시물 등록 페이지 호출");
	}
	
	@PostMapping("/register")
	public String registerNewBoard(MyBoardVO myboard, RedirectAttributes redirectAttr) {
		log.info("컨트롤러 - 게시물 등록: " + myboard);
		
		long bno = myboardService.registerBoard(myboard);
		
		log.info("등록된 게시물의 bno: "+bno);
		
		redirectAttr.addFlashAttribute("result", bno);
		
		return "redirect:/myboard/list";
	}

	
	
}
